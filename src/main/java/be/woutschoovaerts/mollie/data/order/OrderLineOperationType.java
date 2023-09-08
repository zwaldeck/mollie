package be.woutschoovaerts.mollie.data.order;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderLineOperationType {

    ADD,
    UPDATE,
    CANCEL;

    @JsonValue
    public String getJsonValue() {
        return this.name().toLowerCase();
    }
}
