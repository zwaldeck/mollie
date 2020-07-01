package be.feelio.mollie.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfigTest {

    @Test
    void getBearerToken_apiKey() {
        Config config = new Config();
        config.setApiKey("apiKey");
        config.setAccessToken(null);

        assertEquals("apiKey", config.getBearerToken());
    }

    @Test
    void getBearerToken_accessToken() {
        Config config = new Config();
        config.setApiKey("apiKey");
        config.setAccessToken("accessToken");

        assertEquals("accessToken", config.getBearerToken());
    }
}