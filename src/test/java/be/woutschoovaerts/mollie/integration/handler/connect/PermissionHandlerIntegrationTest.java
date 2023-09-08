package be.woutschoovaerts.mollie.integration.handler.connect;

import be.woutschoovaerts.mollie.Client;
import be.woutschoovaerts.mollie.ClientBuilder;
import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.data.permission.Permission;
import be.woutschoovaerts.mollie.data.permission.PermissionListResponse;
import be.woutschoovaerts.mollie.data.permission.PermissionResponse;
import be.woutschoovaerts.mollie.exception.MollieException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static be.woutschoovaerts.mollie.IntegrationTestConstants.API_KEY;
import static be.woutschoovaerts.mollie.IntegrationTestConstants.ORGANISATION_TOKEN;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PermissionHandlerIntegrationTest {

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