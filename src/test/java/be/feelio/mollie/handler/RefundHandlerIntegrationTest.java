package be.feelio.mollie.handler;

import be.feelio.mollie.Client;
import be.feelio.mollie.ClientBuilder;
import be.feelio.mollie.exception.MollieException;
import be.feelio.mollie.json.common.Amount;
import be.feelio.mollie.json.common.Pagination;
import be.feelio.mollie.json.request.PaymentRequest;
import be.feelio.mollie.json.request.RefundRequest;
import be.feelio.mollie.json.response.PaymentResponse;
import be.feelio.mollie.json.response.RefundListResponse;
import be.feelio.mollie.json.response.RefundResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static be.feelio.mollie.handler.IntegrationTestConstants.API_KEY;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RefundHandlerIntegrationTest {

    private Client client;

    @BeforeEach
    void setUp() {
        client = new ClientBuilder().withApiKey(API_KEY).build();
    }

    @Test
    @Disabled // We need to have a payment that is paid, this is not doable with rest api
    void createRefund() throws MollieException {
        RefundRequest refundRequest = RefundRequest.builder()
                .amount(Amount.builder()
                        .currency("EUR")
                        .value("10.00")
                        .build())
                .build();

        RefundResponse refundResponse = client.refunds().createRefund("tr_yHhsywCeRa", refundRequest);

        assertNotNull(refundRequest);
    }

    @Test
    @Disabled
    void getRefund() throws MollieException {
        RefundResponse refund = client.refunds().getRefund("tr_yHhsywCeRa", "re_GyEqNRWjHJ");

        assertNotNull(refund);
    }

    @Test
    @Disabled
    void cancelRefund() throws MollieException {
        client.refunds().cancelRefund("tr_yHhsywCeRa", "re_GyEqNRWjHJ");
    }

    @Test
    void getRefunds_forPayment() throws MollieException {
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

        Pagination<RefundListResponse> refunds = client.refunds().listRefunds(payment.getId());

        assertNotNull(refunds);
    }

    @Test
    void getRefunds_all() throws MollieException {
        Pagination<RefundListResponse> refunds = client.refunds().listRefunds();

        assertNotNull(refunds);
    }
}