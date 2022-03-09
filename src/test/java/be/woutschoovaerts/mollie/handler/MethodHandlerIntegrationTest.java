package be.woutschoovaerts.mollie.handler;

import be.woutschoovaerts.mollie.Client;
import be.woutschoovaerts.mollie.ClientBuilder;
import be.woutschoovaerts.mollie.data.common.Amount;
import be.woutschoovaerts.mollie.data.common.Locale;
import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.data.method.MethodListRequest;
import be.woutschoovaerts.mollie.data.method.MethodListResponse;
import be.woutschoovaerts.mollie.data.method.MethodResource;
import be.woutschoovaerts.mollie.data.method.MethodResponse;
import be.woutschoovaerts.mollie.data.payment.PaymentMethod;
import be.woutschoovaerts.mollie.data.payment.SequenceType;
import be.woutschoovaerts.mollie.exception.MollieException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static be.woutschoovaerts.mollie.IntegrationTestConstants.API_KEY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MethodHandlerIntegrationTest {

    private Client client;

    @BeforeEach
    void setUp() {
        client = new ClientBuilder().withApiKey(API_KEY).build();
    }

    @Test
    void listMethods() throws MollieException {
        Pagination<MethodListResponse> methods = client.methods().listMethods();

        assertNotNull(methods);
    }

    @Test
    void listMethodsParams() throws MollieException {
        final MethodListRequest methodListRequest = MethodListRequest.builder()
                .includeWallets(Optional.of(PaymentMethod.APPLE_PAY))
                .locale(Optional.of(Locale.nl_BE))
                .billingCountry(Optional.of("BE"))
                .amount(Optional.of(new Amount("EUR", new BigDecimal("10.00"))))
                .build();

        assertEquals(methodListRequest.toQueryParams(), "?amount%5Bcurrency%5D=EUR&amount%5Bvalue%5D=10.00&locale=nl_BE&sequenceType=oneoff&billingCountry=BE&resource=payments&includeWallets=applepay");
    }

    @Test
    void listMethodsParams2() throws MollieException {
        final MethodListRequest methodListRequest = MethodListRequest.builder()
                .includeWallets(Optional.of(PaymentMethod.APPLE_PAY))
                .sequenceType(Optional.of(SequenceType.FIRST))
                .locale(Optional.of(Locale.nl_BE))
                .billingCountry(Optional.of("BE"))
                .amount(Optional.of(new Amount("EUR", new BigDecimal("10.00"))))
                .resource(Optional.of(MethodResource.ORDERS))
                .build();

        assertEquals(methodListRequest.toQueryParams(), "?amount%5Bcurrency%5D=EUR&amount%5Bvalue%5D=10.00&locale=nl_BE&sequenceType=first&billingCountry=BE&resource=orders&includeWallets=applepay");
    }

    @Test
    void listMethodsParamsBelgium() throws MollieException {
        final MethodListRequest methodListRequest = MethodListRequest.builder()
                .includeWallets(Optional.of(PaymentMethod.APPLE_PAY))
                .sequenceType(Optional.of(SequenceType.FIRST))
                .locale(Optional.of(Locale.nl_BE))
                .billingCountry(Optional.of("BE"))
                .amount(Optional.of(new Amount("EUR", new BigDecimal("10.00"))))
                .build();

        Pagination<MethodListResponse> methods = client.methods().listMethods(methodListRequest);

        assertNotNull(methods);
    }

    @Test
    void listMethodsParamsNetherlands() throws MollieException {
        final MethodListRequest methodListRequest = MethodListRequest.builder()
                .includeWallets(Optional.of(PaymentMethod.APPLE_PAY))
                .sequenceType(Optional.of(SequenceType.FIRST))
                .locale(Optional.of(Locale.nl_NL))
                .billingCountry(Optional.of("NL"))
                .amount(Optional.of(new Amount("EUR", new BigDecimal("10.00"))))
                .build();

        Pagination<MethodListResponse> methods = client.methods().listMethods(methodListRequest);

        assertNotNull(methods);
    }

    @Test
    void getMethod() throws MollieException {
        MethodResponse method = client.methods().getMethod("ideal");

        assertNotNull(method);
    }
}