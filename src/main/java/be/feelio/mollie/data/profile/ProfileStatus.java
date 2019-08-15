package be.feelio.mollie.data.profile;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ProfileStatus {

    UNVERIFIED,
    VERIFIED,
    BLOCKED;

    @JsonValue
    public String getJsonValue() {
        return this.name().toLowerCase();
    }
}
