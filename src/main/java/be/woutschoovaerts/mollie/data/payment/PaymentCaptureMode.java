package be.woutschoovaerts.mollie.data.payment;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PaymentCaptureMode {

    AUTOMATIC,
    MANUAL;

    @JsonValue
    public String getJsonValue() {
        return name().toLowerCase();
    }
}
