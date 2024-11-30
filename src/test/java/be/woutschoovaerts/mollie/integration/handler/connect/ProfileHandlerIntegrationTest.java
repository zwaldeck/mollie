package be.woutschoovaerts.mollie.integration.handler.connect;

import be.woutschoovaerts.mollie.Client;
import be.woutschoovaerts.mollie.ClientBuilder;
import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.data.method.MethodResponse;
import be.woutschoovaerts.mollie.data.payment.PaymentMethod;
import be.woutschoovaerts.mollie.data.profile.*;
import be.woutschoovaerts.mollie.exception.MollieException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static be.woutschoovaerts.mollie.IntegrationTestConstants.API_KEY;
import static be.woutschoovaerts.mollie.IntegrationTestConstants.ORGANISATION_TOKEN;
import static org.junit.jupiter.api.Assertions.*;

class ProfileHandlerIntegrationTest {

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
    void createProfile() throws MollieException {
        ProfileResponse response = create();

        assertNotNull(response);
        assertEquals("profile", response.getResource());
        assertEquals(BusinessCategory.HOME_IMPROVEMENT, response.getBusinessCategory());
    }

    @Test
    @Disabled        // This test works if you fill in an organisation token and remove the @Disabled
    void getProfile() throws MollieException {
        ProfileResponse response = create();

        assertNotNull(response);

        response = client.profiles().getProfile(response.getId());

        assertNotNull(response);
    }

    @Test
    @Disabled        // This test works if you fill in an organisation token and remove the @Disabled
    void updateProfile() throws MollieException {
        ProfileResponse response = create();

        assertNotNull(response);

        String updatedName = response.getName() + "_updated";

        UpdateProfileRequest update = UpdateProfileRequest.builder()
                .name(Optional.of(updatedName))
                .build();

        response = client.profiles().updateProfile(response.getId(), update);

        assertNotNull(response);
        assertEquals(updatedName, response.getName());
    }

    @Test
    @Disabled        // This test works if you fill in an organisation token and remove the @Disabled
    void deleteProfile() throws MollieException {
        ProfileResponse response = create();

        assertNotNull(response);

        client.profiles().deleteProfile(response.getId());
    }

    @Test
    @Disabled        // This test works if you fill in an organisation token and remove the @Disabled
    void enablePaymentMethod() throws MollieException {
        ProfileResponse response = create();

        assertNotNull(response);

        MethodResponse methodResponse = client.profiles()
                .enablePaymentMethod(response.getId(), PaymentMethod.BANCONTACT);

        assertNotNull(methodResponse);
        assertEquals("method", methodResponse.getResource());
    }

    @Test
    @Disabled        // This test works if you fill in an organisation token and remove the @Disabled
    void getProfiles() throws MollieException {
        ProfileResponse response = create();

        assertNotNull(response);

        Pagination<ProfileListResponse> profiles = client.profiles().getProfiles();

        assertNotNull(profiles);
        assertTrue(profiles.getCount() > 0);
    }

    private ProfileResponse create() throws MollieException {
        ProfileRequest body = ProfileRequest.builder()
                .name("wout")
                .website("https://feelio.be")
                .email("info@thisdoesnotexists123456789azerty.be")
                .phone("+32499999999")
                .businessCategory(Optional.of(BusinessCategory.HOME_IMPROVEMENT))
                .build();

        return client.profiles().createProfile(body);
    }
}