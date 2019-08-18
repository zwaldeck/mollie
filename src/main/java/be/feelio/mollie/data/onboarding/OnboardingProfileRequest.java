package be.feelio.mollie.data.onboarding;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OnboardingProfileRequest {

    @Builder.Default
    private Optional<String> name = Optional.empty();

    @Builder.Default
    private Optional<String> url = Optional.empty();

    @Builder.Default
    private Optional<String> email = Optional.empty();

    @Builder.Default
    private Optional<String> description = Optional.empty();

    @Builder.Default
    private Optional<String> phone = Optional.empty();

    @Builder.Default
    private Optional<Integer> categoryCode = Optional.empty();

}
