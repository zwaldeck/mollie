package be.woutschoovaerts.mollie.data.connect;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TokenType {
    BEARER;

    @JsonValue
    public String getJsonValue() {
        return name().toLowerCase().replace('_', '-');
    }
}
