package be.woutschoovaerts.mollie.data.profile;

import com.fasterxml.jackson.annotation.JsonValue;

public enum IssuerStatus {

    ACTIVATED,
    PENDING_ISSUER;

    @JsonValue
    public String getJsonValue() {
        return name().toLowerCase().replace('_', '-');
    }
}
