package be.woutschoovaerts.mollie.data.organization;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PartnerType {

    OAUTH,
    SIGNUP_LINK,
    USER_AGENT;

    @JsonValue
    public String getJsonValue() {
        return name().toLowerCase().replace("_", "");
    }
}
