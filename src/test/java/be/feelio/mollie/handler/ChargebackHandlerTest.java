package be.feelio.mollie.handler;

import be.feelio.mollie.Client;
import be.feelio.mollie.ClientBuilder;
import be.feelio.mollie.exception.MollieException;
import be.feelio.mollie.data.common.Amount;
import be.feelio.mollie.data.common.Pagination;
import be.feelio.mollie.data.request.PaymentRequest;
import be.feelio.mollie.data.response.ChargebackListResponse;
import be.feelio.mollie.data.response.PaymentResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static be.feelio.mollie.handler.IntegrationTestConstants.API_KEY;
import static org.junit.jupiter.api.Assertions.*;

class ChargebackHandlerTest {

    private Client client;

    @BeforeEach
    void setUp() {
        client = new ClientBuilder().withApiKey(API_KEY).build();
    }

    @Test
    void getChargebacks() throws MollieException {
        Pagination<ChargebackListResponse> response = client.chargebacks().listChargebacks();

        assertNotNull(response);
    }

    @Test
    void getChargebacks_withPaymentId() throws MollieException {
        PaymentRequest request = PaymentRequest.builder()
                .amount(Amount.builder()
                        .currency("EUR")
                        .value("10.00")
                        .build())
                .description("My first payment")
                .redirectUrl(Optional.of("https://webshop.example.org/order/12345/"))
                .webhookUrl(Optional.of("https://webshop.example.org/payments/webhook/"))
                .build();
        PaymentResponse payment = client.payments().createPayment(request);

        assertNotNull(payment);

        Pagination<ChargebackListResponse> response = client.chargebacks().listChargebacks(payment.getId());

        assertNotNull(response);
    }
}