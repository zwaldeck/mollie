package be.feelio.mollie.handler;

import be.feelio.mollie.Client;
import be.feelio.mollie.ClientBuilder;
import be.feelio.mollie.data.onboarding.OnboardingProfileRequest;
import be.feelio.mollie.data.request.OnboardingRequest;
import be.feelio.mollie.exception.MollieException;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static be.feelio.mollie.IntegrationTestConstants.API_KEY;
import static be.feelio.mollie.IntegrationTestConstants.ORGANISATION_TOKEN;
import static org.junit.jupiter.api.Assertions.*;

class OnboardingHandlerTest {

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
                        .name(Optional.of("Update_name" + RandomStringUtils.randomAlphabetic(5)))
                        .build()))
                .build());
    }
}