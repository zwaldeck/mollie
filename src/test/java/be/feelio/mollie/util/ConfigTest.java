package be.feelio.mollie.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfigTest {

    @Test
    void getBearerToken_apiKey() {
        Config.getInstance().setApiKey("apiKey");
        Config.getInstance().setAccessToken(null);

        assertEquals("apiKey", Config.getInstance().getBearerToken());
    }

    @Test
    void getBearerToken_accessToken() {
        Config.getInstance().setApiKey("apiKey");
        Config.getInstance().setAccessToken("accessToken");

        assertEquals("accessToken", Config.getInstance().getBearerToken());
    }
}