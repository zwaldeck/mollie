package be.woutschoovaerts.mollie.data.onboarding;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OnboardingStatus {

    NEEDS_DATA("needs-data"),
    IN_REVIEW("in-review"),
    COMPLETED("completed");

    private final String jsonValue;

    @JsonValue
    public String getJsonValue() {
        return jsonValue;
    }
}
