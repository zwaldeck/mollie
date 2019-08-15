package be.feelio.mollie.handler;

import be.feelio.mollie.Client;
import be.feelio.mollie.ClientBuilder;
import be.feelio.mollie.data.common.Pagination;
import be.feelio.mollie.data.response.SettlementListResponse;
import be.feelio.mollie.data.response.SettlementResponse;
import be.feelio.mollie.exception.MollieException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static be.feelio.mollie.IntegrationTestConstants.API_KEY;
import static be.feelio.mollie.IntegrationTestConstants.ORGANISATION_TOKEN;
import static org.junit.jupiter.api.Assertions.*;

class SettlementHandlerTest {

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
    void getSettlement() throws MollieException {
        Pagination<SettlementListResponse> list = client.settlements().getSettlements();

        assertNotNull(list);

        SettlementResponse response = client.settlements()
                .getSettlement(list.getEmbedded().getSettlements().get(0).getId());

        assertNotNull(response);
    }

    @Test
    @Disabled        // This test works if you fill in an organisation token and remove the @Disabled
    void getSettlements() throws MollieException {
        Pagination<SettlementListResponse> response = client.settlements().getSettlements();

        assertNotNull(response);
    }
}