package be.feelio.mollie.handler;

import be.feelio.mollie.Client;
import be.feelio.mollie.ClientBuilder;
import be.feelio.mollie.data.common.Amount;
import be.feelio.mollie.data.common.Pagination;
import be.feelio.mollie.data.enums.Locale;
import be.feelio.mollie.data.enums.OrderStatus;
import be.feelio.mollie.data.enums.PaymentMethod;
import be.feelio.mollie.data.order.*;
import be.feelio.mollie.data.request.*;
import be.feelio.mollie.data.response.*;
import be.feelio.mollie.exception.MollieException;
import be.feelio.mollie.util.QueryParams;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.*;

import static be.feelio.mollie.IntegrationTestConstants.API_KEY;
import static org.junit.jupiter.api.Assertions.*;

class OrderHandlerTest {

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
    void cancelOrder() throws MollieException {
        OrderResponse order = create();

        assertNotNull(order);

        order = client.orders().cancelOrder(order.getId());

        assertNotNull(order);
        assertEquals(OrderStatus.CANCELED, order.getStatus());
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
        OrderRequest orderRequest = OrderRequest.builder()
                .amount(Amount.builder()
                        .currency("EUR")
                        .value("10.00")
                        .build())
                .orderNumber(RandomStringUtils.randomNumeric(5))
                .lines(Collections.singletonList(createOrderLineRequest()))
                .billingAddress(OrderAddressRequest.builder()
                        .givenName("John")
                        .familyName("Doe")
                        .email("john.doe@feelio.be")
                        .streetAndNumber("wetstraat 1")
                        .postalCode("1000")
                        .city("Brussels")
                        .country("BE")
                        .build())
                .locale(Locale.nl_BE)
                .method(Optional.of(Collections.singletonList(PaymentMethod.BANK_TRANSFER)))
                .redirectUrl(Optional.of("https://webshop.example.org/order/12345/"))
                .build();

        return client.orders().createOrder(orderRequest);
    }

    private OrderLineRequest createOrderLineRequest() {
        return OrderLineRequest.builder()
                .name("Orderline name")
                .quantity(10)
                .unitPrice(Amount.builder()
                        .currency("EUR")
                        .value("1.00")
                        .build())
                .totalAmount(Amount.builder()
                        .currency("EUR")
                        .value("10.00")
                        .build())
                .vatRate("21.00")
                .vatAmount(Amount.builder()
                        .currency("EUR")
                        .value("1.74")
                        .build())
                .sku(Optional.of(RandomStringUtils.randomAlphabetic(5)))
                .build();
    }
}