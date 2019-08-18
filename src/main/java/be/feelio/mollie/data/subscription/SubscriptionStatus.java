package be.feelio.mollie.data.subscription;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SubscriptionStatus {

    PENDING("pending"),
    ACTIVE("active"),
    CANCELED("canceled"),
    SUSPENDED("suspended"),
    COMPLETED("completed");

    private final String jsonValue;

    @JsonValue
    public String getJsonValue() {
        return jsonValue;
    }
}
