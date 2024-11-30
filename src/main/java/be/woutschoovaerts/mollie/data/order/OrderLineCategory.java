package be.woutschoovaerts.mollie.data.order;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderLineCategory {

    MEAL,
    ECO,
    GIFT,
    SPORTS,
    ADDITIONAL,
    CONSUME;

    @JsonValue
    public String getJsonValue() {
        return this.name().toLowerCase();
    }
}
