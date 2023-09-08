package be.woutschoovaerts.mollie.handler.business;

import be.woutschoovaerts.mollie.util.QueryParams;
import be.woutschoovaerts.mollie.util.RestService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BalanceHandlerTest {

    @Mock
    private RestService restService;

    @InjectMocks
    private BalanceHandler handler;

    @Test
    void getPrimaryBalance() throws Exception {
        String uri = "/balances/primary";

        handler.getPrimaryBalance();

        verify(restService).get(eq(uri), any(QueryParams.class), eq(false), any(TypeReference.class));
    }

    @Test
    void getBalance() throws Exception {
        String uri = "/balances/balance_id";

        handler.getBalance("balance_id");

        verify(restService).get(eq(uri), any(QueryParams.class), eq(false), any(TypeReference.class));
    }

    @Test
    void getBalances() throws Exception {
        String uri = "/balances";

        handler.getBalances();

        verify(restService).get(eq(uri), any(QueryParams.class), eq(false), any(TypeReference.class));
    }

    @Test
    void getPrimaryBalanceReport() throws Exception {
        String uri = "/balances/primary/report";

        handler.getPrimaryBalanceReport();

        verify(restService).get(eq(uri), any(QueryParams.class), eq(false), any(TypeReference.class));
    }

    @Test
    void getBalanceReport() throws Exception {
        String uri = "/balances/balance_id/report";

        handler.getBalanceReport("balance_id");

        verify(restService).get(eq(uri), any(QueryParams.class), eq(false), any(TypeReference.class));
    }

    @Test
    void getPrimaryBalanceTransactions() throws Exception {
        String uri = "/balances/primary/transactions";

        handler.getPrimaryBalanceTransactions();

        verify(restService).get(eq(uri), any(QueryParams.class), eq(false), any(TypeReference.class));
    }

    @Test
    void getBalanceTransactions() throws Exception {
        String uri = "/balances/balance_id/transactions";

        handler.getBalanceTransactions("balance_id");

        verify(restService).get(eq(uri), any(QueryParams.class), eq(false), any(TypeReference.class));
    }
}