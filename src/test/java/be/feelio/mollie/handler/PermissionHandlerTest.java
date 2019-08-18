package be.feelio.mollie.handler;

import be.feelio.mollie.Client;
import be.feelio.mollie.ClientBuilder;
import be.feelio.mollie.data.common.Pagination;
import be.feelio.mollie.data.permission.Permission;
import be.feelio.mollie.data.permission.PermissionListResponse;
import be.feelio.mollie.data.permission.PermissionResponse;
import be.feelio.mollie.exception.MollieException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static be.feelio.mollie.IntegrationTestConstants.API_KEY;
import static be.feelio.mollie.IntegrationTestConstants.ORGANISATION_TOKEN;
import static org.junit.jupiter.api.Assertions.*;

class PermissionHandlerTest {

    private Client client;

    @BeforeEach
    void setup() {
        client = new ClientBuilder()
                .withApiKey(API_KEY)
                .withOrganizationToken(ORGANISATION_TOKEN)
                .build();
    }

    @Test
    @Disabled // This test works if you fill in an organisation token and remove the @Disabled
    void getPermission() throws MollieException {
        PermissionResponse response = client.permissions().getPermission(Permission.CUSTOMERS_READ);

        assertNotNull(response);
        assertTrue(response.isGranted());
    }

    @Test
    @Disabled // This test works if you fill in an organisation token and remove the @Disabled
    void getPermissions() throws MollieException {
        Pagination<PermissionListResponse> response = client.permissions().getPermissions();

        assertNotNull(response);
    }
}