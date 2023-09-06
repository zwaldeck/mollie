package be.woutschoovaerts.mollie.handler.business;

import be.woutschoovaerts.mollie.Client;
import be.woutschoovaerts.mollie.ClientBuilder;
import be.woutschoovaerts.mollie.data.balance.BalanceResponse;
import be.woutschoovaerts.mollie.data.balance.BalancesListResponse;
import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.exception.MollieException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static be.woutschoovaerts.mollie.IntegrationTestConstants.API_KEY;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// TODO: Figure out how to integration test with the connect flow
class BalanceHandlerTest {

    private Client client;

    @BeforeEach
    void setup() {
        client = new ClientBuilder().withApiKey(API_KEY).build();
    }

    @Test
    @Disabled
    void getBalance() throws MollieException  {
        BalanceResponse balance = client.balances().getBalance("");

        assertNotNull(balance);
    }

    @Test
    @Disabled
    void listBalance() throws MollieException  {
        Pagination<BalancesListResponse> balances = client.balances().getBalances();

        assertNotNull(balances);
    }
}