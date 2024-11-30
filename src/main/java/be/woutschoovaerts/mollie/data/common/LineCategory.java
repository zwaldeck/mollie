package be.woutschoovaerts.mollie.data.common;

import com.fasterxml.jackson.annotation.JsonValue;

public enum LineCategory {
    GIFT,
    ECO,
    MEAL,
    SPORT_CULTURE;

    @JsonValue
    public String getJsonValue() {
        return name().toLowerCase();
    }
}
