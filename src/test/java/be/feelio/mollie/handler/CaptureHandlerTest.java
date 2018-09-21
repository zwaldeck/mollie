package be.feelio.mollie.handler;

import be.feelio.mollie.Client;
import be.feelio.mollie.ClientBuilder;
import be.feelio.mollie.exception.MollieException;
import be.feelio.mollie.json.common.Amount;
import be.feelio.mollie.json.common.Pagination;
import be.feelio.mollie.json.request.PaymentRequest;
import be.feelio.mollie.json.response.CaptureListResponse;
import be.feelio.mollie.json.response.PaymentResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static be.feelio.mollie.handler.IntegrationTestConstants.API_KEY;
import static org.junit.jupiter.api.Assertions.*;

class CaptureHandlerTest {

    private Client client;

    @BeforeEach
    void setup() {
        client = new ClientBuilder().withApiKey(API_KEY).build();
    }

    @Test
    void getCaptures() throws MollieException {
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

        Pagination<CaptureListResponse> response = client.captures().listCaptures(payment.getId());

        assertNotNull(response);
    }
}