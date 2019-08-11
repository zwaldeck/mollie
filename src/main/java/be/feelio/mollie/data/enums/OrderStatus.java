package be.feelio.mollie.data.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderStatus {

    CREATED,
    PAID,
    AUTHORIZED,
    CANCELED,
    SHIPPING,
    COMPLETED,
    EXPIRED;

    @JsonValue
    public String getJsonValue() {
        return this.name().toLowerCase();
    }
}
