package be.woutschoovaerts.mollie.data.method;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MethodStatus {

    ACTIVATED,
    PENDING_BOARDING,
    PENDING_REVIEW,
    PENDING_EXTERNAL,
    REJECTED,
    NULL;

    @JsonValue
    public String getJsonValue() {
        return name().toLowerCase().replace('_', '-');
    }
}
