package be.feelio.mollie.data.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum  ProfileReviewStatus {

    PENDING,
    REJECTED;

    @JsonValue
    public String getJsonValue() {
        return this.name().toLowerCase();
    }
}
