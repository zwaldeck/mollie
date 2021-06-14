package be.woutschoovaerts.mollie.util;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public final class Config {

    @Getter
    @Setter
    private String apiKey;

    @Getter
    @Setter
    private String accessToken;

    @Getter
    @Setter
    private boolean testMode;

    @Setter
    private String userAgent;

    public String getBearerToken() {
        return StringUtils.isBlank(accessToken) ? apiKey : accessToken;
    }

    public boolean shouldAddTestMode() {
        return !StringUtils.isBlank(accessToken) && testMode;
    }

    public Optional<String> getUserAgent() {
        return Optional.ofNullable(userAgent);
    }
}
