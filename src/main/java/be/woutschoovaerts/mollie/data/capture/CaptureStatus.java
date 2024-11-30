package be.woutschoovaerts.mollie.data.capture;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CaptureStatus {

    PENDING,
    SUCCEEDED,
    FAILED;

    @JsonValue
    public String getJsonValue() {
        return name().toLowerCase();
    }
}
