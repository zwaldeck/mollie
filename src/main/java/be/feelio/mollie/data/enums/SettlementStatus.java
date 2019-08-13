package be.feelio.mollie.data.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SettlementStatus {

    OPEN("open"),
    PENDING("pending"),
    PAID_OUT("paidout"),
    FAILED("failed");

    private final String jsonValue;

    @JsonValue
    public String getJsonValue() {
        return jsonValue;
    }
}
