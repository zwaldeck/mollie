package be.woutschoovaerts.mollie.handler;

import be.woutschoovaerts.mollie.Client;
import be.woutschoovaerts.mollie.ClientBuilder;
import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.data.method.MethodListResponse;
import be.woutschoovaerts.mollie.data.method.MethodResponse;
import be.woutschoovaerts.mollie.exception.MollieException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static be.woutschoovaerts.mollie.IntegrationTestConstants.API_KEY;
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
    void listAllMethods() throws MollieException {
        Pagination<MethodListResponse> methods = client.methods().listAllMethods();

        assertNotNull(methods);
    }

    @Test
    void getMethod() throws MollieException {
        MethodResponse method = client.methods().getMethod("ideal");

        assertNotNull(method);
    }
}