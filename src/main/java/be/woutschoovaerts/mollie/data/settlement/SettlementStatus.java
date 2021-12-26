package be.woutschoovaerts.mollie.data.settlement;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SettlementStatus {

    OPEN,
    PENDING,
    PAID_OUT,
    FAILED;

    @JsonValue
    public String getJsonValue() {
        return name().toLowerCase().replace("_", "");
    }
}
