package be.feelio.mollie;

import be.feelio.mollie.util.Config;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OAuthAwareObjectMapperTest {

    private static final String JSON_TO_POJO = "{\n" +
            "  \"name\": \"My Name\"\n" +
            "}";

    private static final TestPojo POJO = new TestPojo("My Name");

    private OAuthAwareObjectMapper objectMapper = new OAuthAwareObjectMapper();

    @Test
    void readValue() {
        TestPojo pojo = objectMapper.readValue(JSON_TO_POJO, TestPojo.class);
        assertEquals("My Name", pojo.getName());
    }

    @Test
    void writeValue_withTestMode() {
        Config.getInstance().setAccessToken("access_token");
        Config.getInstance().setTestMode(true);

        String result = objectMapper.writeValue(POJO);

        assertNotNull(result);
        assertTrue(result.contains("testmode"));
    }

    @Test
    void writeValue_noTestMode() {
        Config.getInstance().setAccessToken("access_token");
        Config.getInstance().setTestMode(false);

        String result = objectMapper.writeValue(POJO);

        assertNotNull(result);
        assertFalse(result.contains("testmode"));
    }

    @Test
    void writeValue_noOAuth() {
        Config.getInstance().setApiKey("api-key");

        String result = objectMapper.writeValue(POJO);

        assertNotNull(result);
        assertFalse(result.contains("testmode"));
    }
}