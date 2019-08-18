package be.feelio.mollie.data.refund;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RefundStatus {

    QUEUED("queued"),
    PENDING("pending"),
    PROCESSING("processing"),
    REFUNDED("refunded"),
    FAILED("failed");

    private final String jsonValue;

    @JsonValue
    public String getJsonValue() {
        return jsonValue;
    }
}
