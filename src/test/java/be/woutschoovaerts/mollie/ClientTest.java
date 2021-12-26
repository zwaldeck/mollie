package be.woutschoovaerts.mollie;

import java.util.Optional;

import kong.unirest.Unirest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void constructor() {
        Client client = new Client("apiKey");

        assertEquals("https://api.mollie.com/v2", client.getEndpoint());
        assertEquals("apiKey", client.getConfig().getApiKey());
    }

    @Test
    void setAccessToken() {
        Client client = new Client("apiKey");

        client.setAccessToken("access_token");

        assertEquals("access_token", client.getConfig().getAccessToken());
    }

    @Test
    void revokeAccessToken() {
        Client client = new Client("apiKey");

        client.setAccessToken("access_token");

        assertEquals("access_token", client.getConfig().getAccessToken());

        client.revokeAccessToken();

        assertNull(client.getConfig().getAccessToken());
    }

    @Test
    void enableTestMode() {
        Client client = new Client("apiKey");
        client.enableTestMode();

        assertTrue(client.getConfig().isTestMode());
    }

    @Test
    void disableTestMode() {
        Client client = new Client("apiKey");
        client.enableTestMode();
        client.disableTestMode();

        assertFalse(client.getConfig().isTestMode());
    }

    @Test
    void setUserAgentString() {
        Client client = new Client("apiKey");
        client.setUserAgentString("user_agent/version");

        assertEquals(Optional.of("user_agent/version"), client.getConfig().getUserAgentString());
    }

    @Test
    void defaultUserAgentString() {
        Client client = new Client("apiKey");

        assertEquals(Optional.empty(), client.getConfig().getUserAgentString());
    }

    @Test
    void twoClients() {
        Client client1 = new Client("apiKey1");
        Client client2 = new Client("apiKey2");

        assertEquals("apiKey1", client1.getConfig().getApiKey());
        assertEquals("apiKey2", client2.getConfig().getApiKey());
    }

    @Test
    void clientWithProxy() {
        ClientProxy clientProxy = ClientProxy.builder()
                .host("localhost")
                .port(9999)
                .username("wout")
                .password("securityIsKey")
                .build();
        Client client = new Client("apiKey1", clientProxy);

        assertEquals("apiKey1", client.getConfig().getApiKey());
        assertEquals("localhost", Unirest.config().getProxy().getHost());
        assertEquals(9999, Unirest.config().getProxy().getPort());
        assertEquals("wout", Unirest.config().getProxy().getUsername());
        assertEquals("securityIsKey", Unirest.config().getProxy().getPassword());
    }

}