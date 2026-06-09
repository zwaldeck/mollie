package be.woutschoovaerts.mollie.data.settlement;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SettlementStatus {

    OPEN("open"),
    PENDING("pending"),
    PROCESSING_AT_BANK("processing-at-bank"),
    PAID_OUT("paidout"),
    FAILED("failed");

    private final String value;

    @JsonValue
    public String getJsonValue() {
        return value;
    }
}
