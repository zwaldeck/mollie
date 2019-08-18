package be.feelio.mollie.data.payment;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SequenceType {

    ONE_OFF("oneoff"),
    FIRST("first"),
    RECURRING("recurring");

    private final String value;

    @JsonValue
    public String getJsonValue() {
        return value;
    }
}
