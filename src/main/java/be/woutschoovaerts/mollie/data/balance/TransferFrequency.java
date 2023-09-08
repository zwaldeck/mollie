package be.woutschoovaerts.mollie.data.balance;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TransferFrequency {

    DAILY,
    TWICE_A_WEEK,
    EVERY_MONDAY,
    EVERY_TUESDAY,
    EVERY_WEDNESDAY,
    EVERY_THURSDAY,
    EVERY_FRIDAY,
    TWICE_A_MONTH,
    MONTHLY,
    NEVER;

    @JsonValue
    public String getJsonValue() {
        return name().toLowerCase().replace("_", "-");
    }
}
