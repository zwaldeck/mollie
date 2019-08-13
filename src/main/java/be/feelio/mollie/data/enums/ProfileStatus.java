package be.feelio.mollie.data.enums;

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
