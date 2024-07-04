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
    private String userAgentString;

    @Setter
    private String idempotencyKey;

    @Setter
    private String baseUrl;

    public String getBearerToken() {
        return StringUtils.isBlank(accessToken) ? apiKey : accessToken;
    }

    public boolean shouldAddTestMode() {
        return !StringUtils.isBlank(accessToken) && testMode;
    }

    public Optional<String> getUserAgentString() {
        return Optional.ofNullable(userAgentString);
    }

    public Optional<String> getIdempotencyKey() {
        return StringUtils.isNotBlank(idempotencyKey) ? Optional.of(idempotencyKey) : Optional.empty();
    }

    public Optional<String> getBaseUrl() {
        return StringUtils.isNotBlank(baseUrl) ? Optional.of(baseUrl) : Optional.empty();
    }
}
