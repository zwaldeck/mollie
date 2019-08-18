package be.feelio.mollie;

import be.feelio.mollie.util.Config;
import org.junit.jupiter.api.Test;

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
    void build_withOrganizationToken() {
        Client client = new ClientBuilder()
                .withApiKey("my-api-key")
                .withOrganizationToken("org-token")
                .build();

        assertNotNull(client);
        assertEquals("org-token", Config.getInstance().getBearerToken());
    }

    @Test
    void build_withTestMode() {
        Client client = new ClientBuilder()
                .withApiKey("my-api-key")
                .withOrganizationToken("org-token")
                .withTestMode(true)
                .build();

        assertNotNull(client);
        assertTrue(Config.getInstance().isTestMode());
    }
}