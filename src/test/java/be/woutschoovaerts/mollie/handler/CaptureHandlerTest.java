package be.woutschoovaerts.mollie.handler;

import be.woutschoovaerts.mollie.Client;
import be.woutschoovaerts.mollie.ClientBuilder;
import be.woutschoovaerts.mollie.exception.MollieException;
import be.woutschoovaerts.mollie.data.common.Amount;
import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.data.payment.PaymentRequest;
import be.woutschoovaerts.mollie.data.capture.CaptureListResponse;
import be.woutschoovaerts.mollie.data.payment.PaymentResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static be.woutschoovaerts.mollie.IntegrationTestConstants.API_KEY;
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