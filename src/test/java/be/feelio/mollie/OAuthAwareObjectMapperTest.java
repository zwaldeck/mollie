package be.feelio.mollie;

import be.feelio.mollie.util.Config;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OAuthAwareObjectMapperTest {

    private static final String JSON_TO_POJO = "{\n" +
            "  \"name\": \"My Name\"\n" +
            "}";

    private static final TestPojo POJO = new TestPojo("My Name");



    @Test
    void readValue() {
        OAuthAwareObjectMapper objectMapper = new OAuthAwareObjectMapper(new Config());
        TestPojo pojo = objectMapper.readValue(JSON_TO_POJO, TestPojo.class);
        assertEquals("My Name", pojo.getName());
    }

    @Test
    void writeValue_withTestMode() {
        Config config = new Config();
        config.setAccessToken("access_token");
        config.setTestMode(true);
        OAuthAwareObjectMapper objectMapper = new OAuthAwareObjectMapper(config);

        String result = objectMapper.writeValue(POJO);

        assertNotNull(result);
        assertTrue(result.contains("testmode"));
    }

    @Test
    void writeValue_noTestMode() {
        Config config = new Config();
        config.setAccessToken("access_token");
        config.setTestMode(false);
        OAuthAwareObjectMapper objectMapper = new OAuthAwareObjectMapper(config);

        String result = objectMapper.writeValue(POJO);

        assertNotNull(result);
        assertFalse(result.contains("testmode"));
    }

    @Test
    void writeValue_noOAuth() {
        Config config = new Config();
        config.setApiKey("api-key");
        OAuthAwareObjectMapper objectMapper = new OAuthAwareObjectMapper(config);

        String result = objectMapper.writeValue(POJO);

        assertNotNull(result);
        assertFalse(result.contains("testmode"));
    }
}