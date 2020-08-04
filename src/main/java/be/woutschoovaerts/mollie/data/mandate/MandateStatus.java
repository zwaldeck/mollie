package be.woutschoovaerts.mollie.data.mandate;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MandateStatus {

    VALID("valid"),
    PENDING("pending"),
    INVALID("invalid");

    private final String jsonValue;

    @JsonValue
    public String getJsonValue() {
        return jsonValue;
    }
}
