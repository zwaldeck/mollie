package be.woutschoovaerts.mollie.integration.handler.connect;

import be.woutschoovaerts.mollie.Client;
import be.woutschoovaerts.mollie.ClientBuilder;
import be.woutschoovaerts.mollie.data.organization.OrganizationResponse;
import be.woutschoovaerts.mollie.exception.MollieException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static be.woutschoovaerts.mollie.IntegrationTestConstants.API_KEY;
import static be.woutschoovaerts.mollie.IntegrationTestConstants.ORGANISATION_TOKEN;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OrganizationHandlerIntegrationTest {

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
    void getMyOrganization() throws MollieException {
        OrganizationResponse response = client.organizations().getMyOrganization();

        assertNotNull(response);
    }

    @Test
    @Disabled // This test works if you fill in an organisation token and remove the @Disabled
    void getOrganization()  throws MollieException{
        OrganizationResponse myOrganisation = client.organizations().getMyOrganization();
        OrganizationResponse response = client.organizations().getOrganization(myOrganisation.getId());

        assertNotNull(response);
    }
}