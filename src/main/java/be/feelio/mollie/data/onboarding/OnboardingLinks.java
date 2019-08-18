package be.feelio.mollie.data.onboarding;

import be.feelio.mollie.data.common.Link;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OnboardingLinks {

    private Link self;

    private Link dashboard;

    private Link organization;

    private Link documentation;
}
