package be.woutschoovaerts.mollie.data.balance;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TransferDestinationType {
    BANK_ACCOUNT;

    @JsonValue
    public String getJsonValue() {
        return name().toLowerCase().replace("_", "-");
    }
}
