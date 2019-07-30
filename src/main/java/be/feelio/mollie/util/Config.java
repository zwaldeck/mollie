package be.feelio.mollie.util;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

public class Config {

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

    public String getBearerToken() {
        return StringUtils.isBlank(accessToken) ? apiKey : accessToken;
    }
}
