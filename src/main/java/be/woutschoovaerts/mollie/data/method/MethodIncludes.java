package be.woutschoovaerts.mollie.data.method;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MethodIncludes {
    ISSUERS("issuers"),
    PRICING("pricing");

    private final String value;

    @JsonValue
    public String getJsonValue() {
        return value;
    }
}
