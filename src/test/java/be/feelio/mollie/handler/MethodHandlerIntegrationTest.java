package be.feelio.mollie.handler;

import be.feelio.mollie.Client;
import be.feelio.mollie.ClientBuilder;
import be.feelio.mollie.exception.MollieException;
import be.feelio.mollie.data.common.Pagination;
import be.feelio.mollie.data.response.MethodListResponse;
import be.feelio.mollie.data.response.MethodResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static be.feelio.mollie.handler.IntegrationTestConstants.API_KEY;
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
    void getMethod() throws MollieException {
        MethodResponse method = client.methods().getMethod("ideal");

        assertNotNull(method);
    }
}