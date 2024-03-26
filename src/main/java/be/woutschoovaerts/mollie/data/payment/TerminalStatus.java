package be.woutschoovaerts.mollie.data.payment;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TerminalStatus {
    PENDING,
    ACTIVE,
    INACTIVE;

    @JsonValue
    public String getJsonValue() {
        return name().toLowerCase().replace('_', '-');
    }
}
