package be.woutschoovaerts.mollie.data.payment;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PaymentRouteDestinationType {

    ORGANIZATION;

    @JsonValue
    public String getJsonValue() {
        return name().toLowerCase();
    }
}
