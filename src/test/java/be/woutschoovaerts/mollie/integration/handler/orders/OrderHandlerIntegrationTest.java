package be.woutschoovaerts.mollie.integration.handler.orders;

import be.woutschoovaerts.mollie.Client;
import be.woutschoovaerts.mollie.ClientBuilder;
import be.woutschoovaerts.mollie.data.common.AddressRequest;
import be.woutschoovaerts.mollie.data.common.Amount;
import be.woutschoovaerts.mollie.data.common.Locale;
import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.data.order.*;
import be.woutschoovaerts.mollie.data.payment.PaymentMethod;
import be.woutschoovaerts.mollie.data.payment.PaymentResponse;
import be.woutschoovaerts.mollie.data.refund.RefundResponse;
import be.woutschoovaerts.mollie.exception.MollieException;
import be.woutschoovaerts.mollie.util.QueryParams;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static be.woutschoovaerts.mollie.IntegrationTestConstants.API_KEY;
import static org.junit.jupiter.api.Assertions.*;

class OrderHandlerIntegrationTest {

    private static final int ORDER_MAX_EXPIRY_DAYS = 100;

    private Client client;

    @BeforeEach
    void setup() {
        client = new ClientBuilder().withApiKey(API_KEY).build();
    }

    @Test
    void createOrder() throws MollieException {
        OrderResponse response = create();

        assertNotNull(response);
    }

    @Test
    void getOrder() throws MollieException {
        OrderResponse order = create();

        assertNotNull(order);

        QueryParams params = new QueryParams();
        params.put("embed", "payments,refunds");

        order = client.orders().getOrder(order.getId(), params);

        assertNotNull(order);
    }

    @Test
    void createOrderLineWithSpecificCategory() throws MollieException {
        OrderRequest orderRequest = createOrderRequest();

        orderRequest.getLines().get(0).setCategory(Optional.of(OrderLineCategory.ECO));

        OrderResponse orderResponse = client.orders().createOrder(orderRequest);

        // can't assert line category is persisted correctly, it's not included in Mollie's order response
        assertNotNull(orderResponse);
    }

    @Test
    void getOrders() throws MollieException {
        OrderResponse order = create();

        assertNotNull(order);

        Pagination<OrderListResponse> orders = client.orders().getOrders();

        assertNotNull(orders);
        assertTrue(orders.getCount() > 0);
    }

    @Test
    void updateOrder() throws MollieException {
        OrderResponse order = create();

        assertNotNull(order);

        OrderUpdateRequest updateRequest = OrderUpdateRequest.builder()
                .orderNumber(Optional.of("new_order_number-" + order.getOrderNumber()))
                .build();

        order = client.orders().updateOrder(order.getId(), updateRequest);

        assertNotNull(order);
        assertEquals(updateRequest.getOrderNumber().get(), order.getOrderNumber());
    }

    @Test
    void updateOrderLine() throws MollieException {
        OrderResponse order = create();

        assertNotNull(order);

        OrderLineResponse orderLine = order.getLines().get(0);

        OrderLineUpdateRequest request = OrderLineUpdateRequest.builder()
                .name(Optional.of("updated_name_" + orderLine.getName()))
                .build();

        order = client.orders().updateOrderLine(order.getId(), orderLine.getId(), request);

        assertNotNull(order);
        assertEquals(request.getName().get(), order.getLines().get(0).getName());
    }

    @Test
    @Disabled // TODO: Enable again when mollie stops throwing 500s
    void manageOrderLines() throws MollieException {
        OrderResponse order = create();

        assertNotNull(order);

        ManageOrderLineRequest request = ManageOrderLineRequest.builder()
                .operations(List.of(
                        OrderLineOperationRequest.updateOperation(
                                UpdateOrderLineRequest.builder()
                                        .id(order.getLines().get(0).getId())
                                        .name(Optional.of("UPDATED NAME"))
                                        .build()
                        )))
                .build();

        order = client.orders().manageOrderLines(order.getId(), request);

        assertNotNull(order);
        assertEquals("UPDATED NAME", order.getLines().get(0).getName());

    }

    @Test
    @Disabled
    void cancelOrder() throws MollieException {
        OrderResponse order = create();

        assertNotNull(order);

        order = client.orders().cancelOrder(order.getId());

        assertNotNull(order);
        assertEquals(OrderStatus.CANCELED, order.getStatus());
    }

    @Test
    void createOrderWithExpiry() throws MollieException {
        LocalDate maxExpiry = LocalDate.now().plusDays(ORDER_MAX_EXPIRY_DAYS);

        OrderRequest orderRequest = createOrderRequestBuilder()
                .expiresAt(Optional.of(maxExpiry))
                .build();

        OrderResponse response = client.orders().createOrder(orderRequest);

        assertNotNull(response);
    }

    @Test
    void createOrderWithExpiryTooFarInFuture() {
        LocalDate maxExpiry = LocalDate.now().plusDays(ORDER_MAX_EXPIRY_DAYS + 1);

        OrderRequest orderRequest = createOrderRequestBuilder()
                .expiresAt(Optional.of(maxExpiry))
                .build();

        MollieException ex = assertThrows(MollieException.class, () -> client.orders().createOrder(orderRequest));

        assertNotNull(ex);
        assertEquals(422, ex.getDetails().get("status"));
        assertEquals("Unprocessable Entity", ex.getDetails().get("title"));
        assertEquals("Expiry date is too far in the future.", ex.getDetails().get("detail"));
    }

    @Test
    @Disabled // Todo extend the test so we have the correct order line status to cancel
    void cancelOrderLine() throws MollieException {
        OrderResponse order = create();

        assertNotNull(order);

        CancelOrderLinesRequest body = CancelOrderLinesRequest.builder()
                .lines(Arrays.asList(
                        CancelOrderLineRequest.builder()
                        .id(order.getLines().get(0).getId())
                        .build()
                )).build();

        client.orders().cancelOrderLines(order.getId(), body);
    }

    @Test
    @Disabled // Not really possible to integration test, to execute this we should have an expired payment
    void createOrderPayment() throws MollieException {
        OrderResponse order = create();

        assertNotNull(order);

        OrderPaymentRequest body = OrderPaymentRequest.builder()
                .method(Optional.of(Arrays.asList(PaymentMethod.BANK_TRANSFER)))
                .build();

        PaymentResponse response = client.orders().createOrderPayment(order.getId(), body);

        assertNotNull(response);
        assertEquals("payment", response.getResource());
    }

    @Test
    @Disabled // Not really possible to integration test, to execute this we should have a paid or completed order
    void createOrderRefund() throws Exception {
        OrderResponse order = create();

        assertNotNull(order);

        OrderRefundRequest body = OrderRefundRequest.builder()
                .lines(Collections.emptyList())
                .description(Optional.of("A nice refund description"))
                .build();

        RefundResponse response = client.orders().createOrderRefund(order.getId(), body);

        assertNotNull(response);
        assertEquals("A nice refund description", response.getDescription());
    }

    @Test
    void getOrderRefunds() throws MollieException {
        OrderResponse order = create();

        assertNotNull(order);

        Pagination<OrderRefundListResponse> response = client.orders().getOrderRefunds(order.getId());

        assertNotNull(response);
    }

    private OrderResponse create() throws MollieException {
        OrderRequest orderRequest = createOrderRequest();

        return client.orders().createOrder(orderRequest);
    }

    private OrderRequest createOrderRequest() {
        return createOrderRequestBuilder().build();
    }

    private OrderRequest.OrderRequestBuilder createOrderRequestBuilder() {
        return OrderRequest.builder()
                .amount(Amount.builder()
                        .currency("EUR")
                        .value(new BigDecimal("10.00"))
                        .build())
                .orderNumber(RandomStringUtils.secure().nextNumeric(5))
                .lines(Collections.singletonList(createOrderLineRequest()))
                .billingAddress(Optional.of(AddressRequest.builder()
                        .givenName("John")
                        .familyName("Doe")
                        .email("john.doe@z-soft.be")
                        .streetAndNumber("wetstraat 1")
                        .postalCode(Optional.of("1000"))
                        .city("Brussels")
                        .country("BE")
                        .build()))
                .locale(Locale.nl_BE)
                .method(Optional.of(Collections.singletonList(PaymentMethod.BANK_TRANSFER)))
                .redirectUrl(Optional.of("https://webshop.example.org/order/12345/"));
    }

    private OrderLineRequest createOrderLineRequest() {
        return OrderLineRequest.builder()
                .name("Orderline name")
                .quantity(10)
                .unitPrice(Amount.builder()
                        .currency("EUR")
                        .value(new BigDecimal("1.00"))
                        .build())
                .totalAmount(Amount.builder()
                        .currency("EUR")
                        .value(new BigDecimal("10.00"))
                        .build())
                .vatRate("21.00")
                .vatAmount(Amount.builder()
                        .currency("EUR")
                        .value(new BigDecimal("1.74"))
                        .build())
                .sku(Optional.of(RandomStringUtils.secure().nextNumeric(5)))
                .build();
    }
}
