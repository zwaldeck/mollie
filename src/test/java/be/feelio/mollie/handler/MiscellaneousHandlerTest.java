package be.feelio.mollie.handler;

import be.feelio.mollie.Client;
import be.feelio.mollie.ClientBuilder;
import org.junit.jupiter.api.BeforeEach;

import static be.feelio.mollie.IntegrationTestConstants.API_KEY;

class MiscellaneousHandlerTest {

    private Client client;

    @BeforeEach
    void setup() {
        client = new ClientBuilder().withApiKey(API_KEY).build();
    }

}