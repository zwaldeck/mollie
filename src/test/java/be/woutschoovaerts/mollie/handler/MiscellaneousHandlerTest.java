package be.woutschoovaerts.mollie.handler;

import be.woutschoovaerts.mollie.Client;
import be.woutschoovaerts.mollie.ClientBuilder;
import org.junit.jupiter.api.BeforeEach;

import static be.woutschoovaerts.mollie.IntegrationTestConstants.API_KEY;

class MiscellaneousHandlerTest {

    private Client client;

    @BeforeEach
    void setup() {
        client = new ClientBuilder().withApiKey(API_KEY).build();
    }

}