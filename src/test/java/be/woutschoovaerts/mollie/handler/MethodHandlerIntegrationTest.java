package be.woutschoovaerts.mollie.handler;

import be.woutschoovaerts.mollie.Client;
import be.woutschoovaerts.mollie.ClientBuilder;
import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.data.method.MethodListResponse;
import be.woutschoovaerts.mollie.data.method.MethodPricing;
import be.woutschoovaerts.mollie.data.method.MethodResponse;
import be.woutschoovaerts.mollie.exception.MollieException;
import be.woutschoovaerts.mollie.util.QueryParams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static be.woutschoovaerts.mollie.IntegrationTestConstants.API_KEY;
import static org.junit.jupiter.api.Assertions.*;

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
    void listAllMethods() throws MollieException {
        Pagination<MethodListResponse> methods = client.methods().listAllMethods();

        assertNotNull(methods);
    }

    @Test
    void getMethod() throws MollieException {
        MethodResponse method = client.methods().getMethod("ideal");

        assertNotNull(method);
    }

    @Test
    void getMethodPricingWithFeeRegion() throws MollieException {
        QueryParams queryParams = new QueryParams();
        queryParams.put("include", "pricing");

        MethodResponse method = client.methods().getMethod("creditcard", queryParams);
        List<MethodPricing> pricing = method.getPricing();

        assertNotEquals(0, pricing.size());
        for (MethodPricing methodPricing: pricing) {
            assertTrue(methodPricing.getFeeRegion().isPresent());
        }
    }

    @Test
    void getMethodPricingWithoutFeeRegion() throws MollieException {
        QueryParams queryParams = new QueryParams();
        queryParams.put("include", "pricing");

        MethodResponse method = client.methods().getMethod("ideal", queryParams);
        List<MethodPricing> pricing = method.getPricing();

        assertNotEquals(0, pricing.size());
        for (MethodPricing methodPricing: pricing) {
            assertFalse(methodPricing.getFeeRegion().isPresent());
        }
    }
}