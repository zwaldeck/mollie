package be.woutschoovaerts.mollie.data.order;

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
        return name().toLowerCase().replace('_', '-');
    }
}
