package be.feelio.mollie.handler;

import be.feelio.mollie.Client;
import be.feelio.mollie.ClientBuilder;
import be.feelio.mollie.data.common.Amount;
import be.feelio.mollie.data.enums.Locale;
import be.feelio.mollie.data.enums.PaymentMethod;
import be.feelio.mollie.data.order.OrderAddressRequest;
import be.feelio.mollie.data.order.OrderLineRequest;
import be.feelio.mollie.data.request.OrderRequest;
import be.feelio.mollie.data.request.ShipmentRequest;
import be.feelio.mollie.data.response.OrderResponse;
import be.feelio.mollie.data.response.ShipmentResponse;
import be.feelio.mollie.data.shipping.ShipmentLineRequest;
import be.feelio.mollie.data.shipping.ShipmentTrackingRequest;
import be.feelio.mollie.exception.MollieException;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static be.feelio.mollie.handler.IntegrationTestConstants.API_KEY;
import static org.junit.jupiter.api.Assertions.*;

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