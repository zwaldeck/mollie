package be.woutschoovaerts.mollie.data.refund;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RefundStatus {

    QUEUED,
    PENDING,
    CANCELED,
    PROCESSING,
    REFUNDED,
    FAILED;

    @JsonValue
    public String getJsonValue() {
        return name().toLowerCase().replace('_', '-');
    }
}
