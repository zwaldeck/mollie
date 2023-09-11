package be.woutschoovaerts.mollie;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
class ClientBuilderTest {


    @Test
    void build_noApiKeyGiven() {
        assertThrows(IllegalArgumentException.class, () -> new ClientBuilder().build());
    }

    @Test
    void build_success() {
        assertNotNull(new ClientBuilder()
        .withApiKey("my-api-key")
        .build());
    }

    @Test
    void build_success_withProxy() {
        Client client = new ClientBuilder()
                .withApiKey("my-api-key")
                .withProxy(ClientProxy.builder()
                        .host("localhost")
                        .port(9999)
                        .username("wout")
                        .password("securityIsKey")
                        .build())
                .build();

        assertNotNull(client);
        assertTrue(client.getConfig().getProxy().isPresent());
        assertEquals("localhost", client.getConfig().getProxy().get().getHost());
        assertEquals(9999, client.getConfig().getProxy().get().getPort());
        assertEquals("wout", client.getConfig().getProxy().get().getUsername());
        assertEquals("securityIsKey", client.getConfig().getProxy().get().getPassword());
    }

    @Test
    void build_withOrganizationToken() {
        Client client = new ClientBuilder()
                .withApiKey("my-api-key")
                .withOrganizationToken("org-token")
                .build();

        assertNotNull(client);
        assertEquals("org-token", client.getConfig().getBearerToken());
    }

    @Test
    void build_withTestMode() {
        Client client = new ClientBuilder()
                .withApiKey("my-api-key")
                .withOrganizationToken("org-token")
                .withTestMode(true)
                .build();

        assertNotNull(client);
        assertTrue(client.getConfig().isTestMode());
    }

    @Test
    void build_withUserAgentString() {
        Client client = new ClientBuilder()
                .withApiKey("my-api-key")
                .withUserAgent("my-user-agent-string")
                .build();

        assertNotNull(client);
        assertEquals(Optional.of("my-user-agent-string"), client.getConfig().getUserAgentString());
    }
}