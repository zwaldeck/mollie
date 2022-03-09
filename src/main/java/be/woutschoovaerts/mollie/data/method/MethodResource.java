package be.woutschoovaerts.mollie.data.method;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MethodResource {
    ORDERS("orders"),
    PAYMENTS("payments");

    private final String value;

    @JsonValue
    public String getJsonValue() {
        return value;
    }
}
