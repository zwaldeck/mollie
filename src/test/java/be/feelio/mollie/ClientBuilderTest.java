package be.feelio.mollie;

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
}