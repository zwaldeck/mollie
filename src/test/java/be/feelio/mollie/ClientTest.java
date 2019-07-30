package be.feelio.mollie;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void constructor() {
        Client client = new Client("apiKey");

        assertEquals("https://api.mollie.com/v2", client.getEndpoint());
    }

}