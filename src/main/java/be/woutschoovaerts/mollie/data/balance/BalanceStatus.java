package be.woutschoovaerts.mollie.data.balance;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BalanceStatus {
    ACTIVE,
    INACTIVE;

    @JsonValue
    public String getJsonValue() {
        return name().toLowerCase();
    }
}
