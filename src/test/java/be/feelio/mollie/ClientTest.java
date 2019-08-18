package be.feelio.mollie;

import be.feelio.mollie.util.Config;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void constructor() {
        Client client = new Client("apiKey");

        assertEquals("https://api.mollie.com/v2", client.getEndpoint());
        assertEquals("apiKey", Config.getInstance().getApiKey());
    }

    @Test
    void setAccessToken() {
        Client client = new Client("apiKey");

        client.setAccessToken("access_token");

        assertEquals("access_token", Config.getInstance().getAccessToken());
    }

    @Test
    void revokeAccessToken() {
        Client client = new Client("apiKey");

        client.setAccessToken("access_token");

        assertEquals("access_token", Config.getInstance().getAccessToken());

        client.revokeAccessToken();

        assertNull(Config.getInstance().getAccessToken());
    }

    @Test
    void enableTestMode() {
        Client client = new Client("apiKey");
        client.enableTestMode();

        assertTrue(Config.getInstance().isTestMode());
    }

    @Test
    void disableTestMode() {
        Client client = new Client("apiKey");
        client.enableTestMode();
        client.disableTestMode();

        assertFalse(Config.getInstance().isTestMode());
    }

}