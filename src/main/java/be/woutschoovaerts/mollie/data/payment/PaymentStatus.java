package be.woutschoovaerts.mollie.data.payment;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PaymentStatus {
    OPEN,
    CANCELED,
    PENDING,
    AUTHORIZED,
    EXPIRED,
    FAILED,
    PAID;

    @JsonValue
    public String getJsonValue() {
        return name().toLowerCase().replace('_', '-');
    }
}
