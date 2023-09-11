package be.woutschoovaerts.mollie.util;

import be.woutschoovaerts.mollie.ClientProxy;
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

    @Getter
    @Setter
    private Optional<String> userAgentString = Optional.empty();

    @Getter
    @Setter
    private Optional<ClientProxy> proxy = Optional.empty();

    @Setter
    private String idempotencyKey;

    public String getBearerToken() {
        return StringUtils.isBlank(accessToken) ? apiKey : accessToken;
    }

    public boolean shouldAddTestMode() {
        return !StringUtils.isBlank(accessToken) && testMode;
    }

    public Optional<String> getIdempotencyKey() {
        return StringUtils.isNotBlank(idempotencyKey) ? Optional.of(idempotencyKey) : Optional.empty();
    }
}
