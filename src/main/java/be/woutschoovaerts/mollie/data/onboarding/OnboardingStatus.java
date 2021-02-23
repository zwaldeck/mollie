package be.woutschoovaerts.mollie.data.onboarding;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OnboardingStatus {

    NEEDS_DATA,
    IN_REVIEW,
    COMPLETED;

    @JsonValue
    public String getJsonValue() {
        return name().toLowerCase().replace('_', '-');
    }
}
