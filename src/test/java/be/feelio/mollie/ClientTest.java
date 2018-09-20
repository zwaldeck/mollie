package be.feelio.mollie;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void constructor() {
        Client client = new Client("v1", "apiKey");

        assertEquals("https://api.mollie.com/v1", client.getEndpoint());
        assertEquals("apiKey", client.getApiKey());
    }

}