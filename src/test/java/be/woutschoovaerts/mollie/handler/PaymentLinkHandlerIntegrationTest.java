package be.woutschoovaerts.mollie.handler;

import be.woutschoovaerts.mollie.Client;
import be.woutschoovaerts.mollie.ClientBuilder;
import be.woutschoovaerts.mollie.data.common.Amount;
import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.data.paymentLink.PaymentLinkListResponse;
import be.woutschoovaerts.mollie.data.paymentLink.PaymentLinkRequest;
import be.woutschoovaerts.mollie.data.paymentLink.PaymentLinkResponse;
import be.woutschoovaerts.mollie.exception.MollieException;
import be.woutschoovaerts.mollie.util.QueryParams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static be.woutschoovaerts.mollie.IntegrationTestConstants.API_KEY;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentLinkHandlerIntegrationTest {

    private Client client;

    @BeforeEach
    void setup() {
        client = new ClientBuilder().withApiKey(API_KEY).build();
    }

    @Test
    void createPaymentLink_success() throws MollieException {
        PaymentLinkRequest request = PaymentLinkRequest.builder()
                .amount(Amount.builder()
                        .currency("EUR")
                        .value(new BigDecimal("10.00"))
                        .build())
                .description("My first payment")
                .redirectUrl(Optional.of("https://webshop.example.org/order/12345/"))
                .webhookUrl(Optional.of("https://webshop.example.org/payments/webhook/"))
                .build();
        PaymentLinkResponse response = client.paymentLinks().createPaymentLink(request);

        assertNotNull(response);
    }

    @Test
    void createPaymentLink_wrong_body() {
        PaymentLinkRequest request = PaymentLinkRequest.builder()
                .amount(Amount.builder()
                        .currency("CURRENCY THAT NOT EXISTS")
                        .value(new BigDecimal("10.00"))
                        .build())
                .description("My first payment")
                .redirectUrl(Optional.of("https://webshop.example.org/order/12345/"))
                .webhookUrl(Optional.of("https://webshop.example.org/payments/webhook/"))
                .build();
        MollieException ex = assertThrows(MollieException.class, () -> client.paymentLinks().createPaymentLink(request));

        assertNotNull(ex);
        assertEquals(422, ex.getDetails().get("status"));
        assertEquals("Unprocessable Entity", ex.getDetails().get("title"));
        assertEquals("amount.currency", ex.getDetails().get("field"));
    }

    @Test
    void getPaymentLink_success() throws MollieException {
        PaymentLinkRequest request = PaymentLinkRequest.builder()
                .amount(Amount.builder()
                        .currency("EUR")
                        .value(new BigDecimal("10.00"))
                        .build())
                .description("My first payment")
                .redirectUrl(Optional.of("https://webshop.example.org/order/12345/"))
                .webhookUrl(Optional.of("https://webshop.example.org/payments/webhook/"))
                .build();
        PaymentLinkResponse response = client.paymentLinks().createPaymentLink(request);

        assertNotNull(response);

        response = client.paymentLinks().getPaymentLink(response.getId());

        assertNotNull(response);
    }

    @Test
    void listPaymentLinks_success() throws MollieException {
        Pagination<PaymentLinkListResponse> response = client.paymentLinks().listPaymentLinks();

        assertNotNull(response);
        assertTrue(response.getCount() > 0);
        assertTrue(response.getLinks().getSelf().getHref().contains("limit=50"));
    }

    @Test
    void listPaymentLinks_success_withFrom() throws MollieException {
        PaymentLinkRequest request = PaymentLinkRequest.builder()
                .amount(Amount.builder()
                        .currency("EUR")
                        .value(new BigDecimal("10.00"))
                        .build())
                .description("My first payment")
                .redirectUrl(Optional.of("https://webshop.example.org/order/12345/"))
                .webhookUrl(Optional.of("https://webshop.example.org/payments/webhook/"))
                .build();
        PaymentLinkResponse response = client.paymentLinks().createPaymentLink(request);

        assertNotNull(response);

        QueryParams params = new QueryParams();
        params.put("from", response.getId());

        Pagination<PaymentLinkListResponse> responseList = client.paymentLinks().listPaymentLinks(params);

        assertNotNull(response);
        assertTrue(responseList.getCount() > 0);
        assertTrue(responseList.getLinks().getSelf().getHref().contains("limit=50"));
        assertTrue(responseList.getLinks().getSelf().getHref().contains("from=" + response.getId()));
    }

    @Test
    void listPaymentLinks_success_withFrom_withLimit() throws MollieException {
        PaymentLinkRequest request = PaymentLinkRequest.builder()
                .amount(Amount.builder()
                        .currency("EUR")
                        .value(new BigDecimal("10.00"))
                        .build())
                .description("My first payment")
                .redirectUrl(Optional.of("https://webshop.example.org/order/12345/"))
                .webhookUrl(Optional.of("https://webshop.example.org/payments/webhook/"))
                .build();
        PaymentLinkResponse response = client.paymentLinks().createPaymentLink(request);

        assertNotNull(response);

        QueryParams params = new QueryParams();
        params.put("from", response.getId());
        params.put("limit", "10");

        Pagination<PaymentLinkListResponse> responseList = client.paymentLinks().listPaymentLinks(params);

        assertNotNull(response);
        assertTrue(responseList.getCount() <= 10);
        assertTrue(responseList.getLinks().getSelf().getHref().contains("limit=10"));
        assertTrue(responseList.getLinks().getSelf().getHref().contains("from=" + response.getId()));
    }
}
