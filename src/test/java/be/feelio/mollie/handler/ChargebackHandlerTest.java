package be.feelio.mollie.handler;

import be.feelio.mollie.Client;
import be.feelio.mollie.ClientBuilder;
import be.feelio.mollie.exception.MollieException;
import be.feelio.mollie.json.common.Pagination;
import be.feelio.mollie.json.response.ChargebackListResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static be.feelio.mollie.handler.IntegrationTestConstants.API_KEY;
import static org.junit.jupiter.api.Assertions.*;

class ChargebackHandlerTest {

    private Client client;

    @BeforeEach
    void setUp() {
        client = new ClientBuilder().withApiKey(API_KEY).build();
    }

    @Test
    void getChargebacks() throws MollieException {
        Pagination<ChargebackListResponse> response = client.chargebacks().getChargebacks();

        assertNotNull(response);
    }

    @Test
    void getChargebacks_withPaymentId() throws MollieException {
        Pagination<ChargebackListResponse> response = client.chargebacks().getChargebacks("tr_yHhsywCeRa");

        assertNotNull(response);
    }
}