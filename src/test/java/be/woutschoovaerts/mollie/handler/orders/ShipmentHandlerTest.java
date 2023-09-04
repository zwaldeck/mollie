package be.woutschoovaerts.mollie.handler.orders;

import be.woutschoovaerts.mollie.Client;
import be.woutschoovaerts.mollie.ClientBuilder;
import be.woutschoovaerts.mollie.data.common.Amount;
import be.woutschoovaerts.mollie.data.common.Locale;
import be.woutschoovaerts.mollie.data.order.OrderAddressRequest;
import be.woutschoovaerts.mollie.data.order.OrderLineRequest;
import be.woutschoovaerts.mollie.data.order.OrderRequest;
import be.woutschoovaerts.mollie.data.order.OrderResponse;
import be.woutschoovaerts.mollie.data.payment.PaymentMethod;
import be.woutschoovaerts.mollie.data.shipment.ShipmentLineRequest;
import be.woutschoovaerts.mollie.data.shipment.ShipmentRequest;
import be.woutschoovaerts.mollie.data.shipment.ShipmentResponse;
import be.woutschoovaerts.mollie.data.shipment.ShipmentTrackingRequest;
import be.woutschoovaerts.mollie.exception.MollieException;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

import static be.woutschoovaerts.mollie.IntegrationTestConstants.API_KEY;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ShipmentHandlerTest {

    private Client client;

    @BeforeEach
    void setup() {
        client = new ClientBuilder().withApiKey(API_KEY).build();
    }

    @Test
    @Disabled // TODO: We need to figure out how to change the order status so it can be shipped
    void createShipmentTest() throws MollieException {
        ShipmentResponse response = createShipment();

        assertNotNull(response);
    }

    // Todo get shipment test --> Check todo above

    @Test
    void getShipments() throws MollieException {
        OrderResponse order = createOrder();

        assertNotNull(order);

        assertNotNull(client.shipments().getShipments(order.getId()));
    }

    // Todo update shipment test --> Check first todo

    private OrderResponse createOrder() throws MollieException {
        OrderRequest orderRequest = OrderRequest.builder()
                .amount(Amount.builder()
                        .currency("EUR")
                        .value(new BigDecimal("10.00"))
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
                .sku(Optional.of(RandomStringUtils.randomAlphabetic(5)))
                .build();
    }

    private ShipmentResponse createShipment() throws MollieException {
        OrderResponse order = createOrder();

        assertNotNull(order);

        ShipmentRequest request = ShipmentRequest.builder()
                .lines(Collections.emptyList())
                .tracking(Optional.of(ShipmentTrackingRequest.builder()
                        .carrier("PostNL")
                        .code(RandomStringUtils.randomAlphanumeric(5))
                        .build()))
                .build();

        return client.shipments().createShipment(order.getId(), request);
    }

    private ShipmentLineRequest createShipmentLineRequest(String orderLineId) {
        return ShipmentLineRequest.builder()
                .id(orderLineId)
                .build();
    }
}
