package be.woutschoovaerts.mollie.integration.handler.connect;

import be.woutschoovaerts.mollie.Client;
import be.woutschoovaerts.mollie.ClientBuilder;
import be.woutschoovaerts.mollie.data.onboarding.OnboardingProfileRequest;
import be.woutschoovaerts.mollie.data.onboarding.OnboardingRequest;
import be.woutschoovaerts.mollie.exception.MollieException;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static be.woutschoovaerts.mollie.IntegrationTestConstants.API_KEY;
import static be.woutschoovaerts.mollie.IntegrationTestConstants.ORGANISATION_TOKEN;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OnboardingHandlerIntegrationTest {

    private Client client;

    @BeforeEach
    void setup() {
        client = new ClientBuilder()
                .withApiKey(API_KEY)
                .withOrganizationToken(ORGANISATION_TOKEN)
                .build();
    }

    @Test
    @Disabled        // This test works if you fill in an organisation token and remove the @Disabled
    void getOnboardingStatus() throws MollieException {
        assertNotNull(client.onboarding().getOnboardingStatus());
    }

    @Test
    @Disabled        // This test works if you fill in an organisation token and remove the @Disabled
    void submitOnboardingData() throws MollieException{
        client.onboarding().submitOnboardingData(OnboardingRequest.builder()
                .profile(Optional.of(OnboardingProfileRequest.builder()
                        .name(Optional.of("Update_name" + RandomStringUtils.secure().nextNumeric(5)))
                        .build()))
                .build());
    }
}