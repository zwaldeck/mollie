package be.woutschoovaerts.mollie.data.onboarding;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OnboardingOrganizationRequest {

    @Builder.Default
    private Optional<String> name = Optional.empty();

    @Builder.Default
    private Optional<OnboardingOrganizationAddressRequest> address = Optional.empty();

    @Builder.Default
    private Optional<String> registrationNumber = Optional.empty();

    @Builder.Default
    private Optional<String> vatNumber = Optional.empty();
}
