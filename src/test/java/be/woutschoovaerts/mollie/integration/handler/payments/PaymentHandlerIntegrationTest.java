package be.woutschoovaerts.mollie.integration.handler.payments;

import be.woutschoovaerts.mollie.Client;
import be.woutschoovaerts.mollie.ClientBuilder;
import be.woutschoovaerts.mollie.data.common.Amount;
import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.data.payment.*;
import be.woutschoovaerts.mollie.exception.MollieException;
import be.woutschoovaerts.mollie.util.QueryParams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

import static be.woutschoovaerts.mollie.IntegrationTestConstants.API_KEY;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentHandlerIntegrationTest {

    private Client client;

    @BeforeEach
    void setup() {
        client = new ClientBuilder().withApiKey(API_KEY).build();
    }

    @Test
    void createPayment_success() throws MollieException {
        PaymentRequest request = PaymentRequest.builder()
                .amount(Amount.builder()
                        .currency("EUR")
                        .value(new BigDecimal("10.00"))
                        .build())
                .description("My first payment")
                .redirectUrl("https://webshop.example.org/order/12345/")
                .webhookUrl(Optional.of("https://webshop.example.org/payments/webhook/"))
                .build();
        PaymentResponse response = client.payments().createPayment(request);

        assertNotNull(response);
    }

    @Test
    void createPayment_wrong_body() {
        PaymentRequest request = PaymentRequest.builder()
                .amount(Amount.builder()
                        .currency("CURRENCY THAT NOT EXISTS")
                        .value(new BigDecimal("10.00"))
                        .build())
                .description("My first payment")
                .redirectUrl("https://webshop.example.org/order/12345/")
                .webhookUrl(Optional.of("https://webshop.example.org/payments/webhook/"))
                .build();
        MollieException ex = assertThrows(MollieException.class, () -> client.payments().createPayment(request));

        assertNotNull(ex);
        assertEquals(422, ex.getDetails().get("status"));
        assertEquals("Unprocessable Entity", ex.getDetails().get("title"));
        assertEquals("amount.currency", ex.getDetails().get("field"));
    }

    @Test
    void getPayment_success() throws MollieException {
        PaymentRequest request = PaymentRequest.builder()
                .amount(Amount.builder()
                        .currency("EUR")
                        .value(new BigDecimal("10.00"))
                        .build())
                .description("My first payment")
                .redirectUrl("https://webshop.example.org/order/12345/")
                .webhookUrl(Optional.of("https://webshop.example.org/payments/webhook/"))
                .build();
        PaymentResponse response = client.payments().createPayment(request);

        assertNotNull(response);

        response = client.payments().getPayment(response.getId());

        assertNotNull(response);
    }

    @Test
    void updatePayment_success() throws MollieException {
        PaymentRequest request = PaymentRequest.builder()
                .amount(Amount.builder()
                        .currency("EUR")
                        .value(new BigDecimal("10.00"))
                        .build())
                .description("My first payment")
                .redirectUrl("https://webshop.example.org/order/12345/")
                .webhookUrl(Optional.of("https://webshop.example.org/payments/webhook/"))
                .build();
        PaymentResponse response = client.payments().createPayment(request);

        assertNotNull(response);

        UpdatePaymentRequest updatePaymentRequest = UpdatePaymentRequest.builder()
                .description(Optional.of("Updated description"))
                .build();

        response = client.payments().updatePayment(response.getId(), updatePaymentRequest);

        assertNotNull(response);
        assertEquals("Updated description", response.getDescription());
    }

    @Test
    @Disabled
        // TODO enable when we have a cancelable payment method
    void cancelPayment_success() throws MollieException {
        PaymentRequest request = PaymentRequest.builder()
                .amount(Amount.builder()
                        .currency("EUR")
                        .value(new BigDecimal("10.00"))
                        .build())
                .description("My first payment")
                .method(Optional.of(Collections.singletonList(PaymentMethod.PAY_SAFE_CARD)))
                .redirectUrl("https://webshop.example.org/order/12345/")
                .webhookUrl(Optional.of("https://webshop.example.org/payments/webhook/"))
                .build();
        PaymentResponse response = client.payments().createPayment(request);

        assertNotNull(response);
        assertTrue(response.isCancelable());

        response = client.payments().cancelPayment(response.getId());

        assertNotNull(response);
    }

    @Test
    void listPayments_success() throws MollieException {
        Pagination<PaymentListResponse> response = client.payments().listPayments();

        assertNotNull(response);
        assertTrue(response.getCount() > 0);
        assertTrue(response.getLinks().getSelf().getHref().contains("limit=50"));
    }

    @Test
    void listPayments_success_withFrom() throws MollieException {
        PaymentRequest request = PaymentRequest.builder()
                .amount(Amount.builder()
                        .currency("EUR")
                        .value(new BigDecimal("10.00"))
                        .build())
                .description("My first payment")
                .redirectUrl("https://webshop.example.org/order/12345/")
                .webhookUrl(Optional.of("https://webshop.example.org/payments/webhook/"))
                .build();
        PaymentResponse response = client.payments().createPayment(request);

        assertNotNull(response);

        QueryParams params = new QueryParams();
        params.put("from", response.getId());

        Pagination<PaymentListResponse> responseList = client.payments().listPayments(params);

        assertNotNull(response);
        assertTrue(responseList.getCount() > 0);
        assertTrue(responseList.getLinks().getSelf().getHref().contains("limit=50"));
        assertTrue(responseList.getLinks().getSelf().getHref().contains("from=" + response.getId()));
    }

    @Test
    void listPayments_success_withFrom_withLimit() throws MollieException {
        PaymentRequest request = PaymentRequest.builder()
                .amount(Amount.builder()
                        .currency("EUR")
                        .value(new BigDecimal("10.00"))
                        .build())
                .description("My first payment")
                .redirectUrl("https://webshop.example.org/order/12345/")
                .webhookUrl(Optional.of("https://webshop.example.org/payments/webhook/"))
                .build();
        PaymentResponse response = client.payments().createPayment(request);

        assertNotNull(response);

        QueryParams params = new QueryParams();
        params.put("from", response.getId());
        params.put("limit", "10");

        Pagination<PaymentListResponse> responseList = client.payments().listPayments(params);

        assertNotNull(response);
        assertTrue(responseList.getCount() <= 10);
        assertTrue(responseList.getLinks().getSelf().getHref().contains("limit=10"));
        assertTrue(responseList.getLinks().getSelf().getHref().contains("from=" + response.getId()));
    }
}
