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
public class OnboardingOrganizationAddressRequest {

    @Builder.Default
    private Optional<String> streetAndNumber = Optional.empty();

    @Builder.Default
    private Optional<String> postalCode = Optional.empty();

    @Builder.Default
    private Optional<String> city = Optional.empty();

    @Builder.Default
    private Optional<String> country = Optional.empty();
}
