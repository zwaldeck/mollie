package be.woutschoovaerts.mollie;

import kong.unirest.Unirest;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void constructor() {
        Client client = new Client("apiKey");

        assertEquals("apiKey", client.getConfig().getApiKey());

        Unirest.config().shutDown(true);
    }

    @Test
    void setAccessToken() {
        Client client = new Client("apiKey");

        client.setAccessToken("access_token");

        assertEquals("access_token", client.getConfig().getAccessToken());

        Unirest.config().shutDown(true);
    }

    @Test
    void revokeAccessToken() {
        Client client = new Client("apiKey");

        client.setAccessToken("access_token");

        assertEquals("access_token", client.getConfig().getAccessToken());

        client.revokeAccessToken();

        assertNull(client.getConfig().getAccessToken());

        Unirest.config().shutDown(true);
    }

    @Test
    void enableTestMode() {
        Client client = new Client("apiKey");
        client.enableTestMode();

        assertTrue(client.getConfig().isTestMode());

        Unirest.config().shutDown(true);
    }

    @Test
    void disableTestMode() {
        Client client = new Client("apiKey");
        client.enableTestMode();
        client.disableTestMode();

        assertFalse(client.getConfig().isTestMode());

        Unirest.config().shutDown(true);
    }

    @Test
    void setUserAgentString() {
        Client client = new Client("apiKey");
        client.setUserAgentString("user_agent/version");

        assertEquals(Optional.of("user_agent/version"), client.getConfig().getUserAgentString());

        Unirest.config().shutDown(true);
    }

    @Test
    void defaultUserAgentString() {
        Client client = new Client("apiKey");

        assertEquals(Optional.empty(), client.getConfig().getUserAgentString());

        Unirest.config().shutDown(true);
    }

    @Test
    void twoClients() {
        Client client1 = new Client("apiKey1");
        Client client2 = new Client("apiKey2");

        assertEquals("apiKey1", client1.getConfig().getApiKey());
        assertEquals("apiKey2", client2.getConfig().getApiKey());

        Unirest.config().shutDown(true);
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

        Unirest.config().shutDown(true);
    }

}