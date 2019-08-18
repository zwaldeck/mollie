package be.feelio.mollie.util;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

public final class Config {

    @Getter
    private static Config instance = new Config();

    private Config() {
    }

    @Getter
    @Setter
    private String apiKey;

    @Getter
    @Setter
    private String accessToken;

    @Getter
    @Setter
    private boolean testMode;

    public String getBearerToken() {
        return StringUtils.isBlank(accessToken) ? apiKey : accessToken;
    }

    public boolean shouldAddTestMode() {
        return !StringUtils.isBlank(accessToken) && testMode;
    }
}
