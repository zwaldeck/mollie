package be.woutschoovaerts.mollie.data.subscription;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SubscriptionStatus {

    PENDING,
    ACTIVE,
    CANCELED,
    SUSPENDED,
    COMPLETED;

    @JsonValue
    public String getJsonValue() {
        return name().toLowerCase().replace('_', '-');
    }
}
