package be.woutschoovaerts.mollie.handler;

import be.woutschoovaerts.mollie.Client;
import be.woutschoovaerts.mollie.ClientBuilder;
import be.woutschoovaerts.mollie.exception.MollieException;
import be.woutschoovaerts.mollie.data.common.Amount;
import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.data.payment.PaymentRequest;
import be.woutschoovaerts.mollie.data.chargeback.ChargebackListResponse;
import be.woutschoovaerts.mollie.data.payment.PaymentResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static be.woutschoovaerts.mollie.IntegrationTestConstants.API_KEY;
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
                        .value(new BigDecimal("10.00"))
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
