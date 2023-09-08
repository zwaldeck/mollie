package be.woutschoovaerts.mollie.data.common;

import com.fasterxml.jackson.annotation.JsonValue;

public enum RoutingReversalSourceType {

    ORGANIZATION;

    @JsonValue
    public String getJsonValue() {
        return this.name().toLowerCase();
    }
}
