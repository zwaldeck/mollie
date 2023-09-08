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
class SettlementHandlerTest {

    @Mock
    private RestService restService;

    @InjectMocks
    private SettlementHandler handler;

    @Test
    void getSettlement() throws Exception {
        String uri = "/settlements/settlement_id";

        handler.getSettlement("settlement_id");

        verify(restService).get(eq(uri), any(QueryParams.class), eq(false), any(TypeReference.class));
    }

    @Test
    void getSettlements() throws Exception {
        String uri = "/settlements";

        handler.getSettlements();

        verify(restService).get(eq(uri), any(QueryParams.class), eq(false), any(TypeReference.class));
    }

    @Test
    void getNextSettlement() throws Exception {
        String uri = "/settlements/next";

        handler.getNextSettlement();

        verify(restService).get(eq(uri), any(QueryParams.class), eq(false), any(TypeReference.class));
    }

    @Test
    void getOpenSettlement() throws Exception {
        String uri = "/settlements/open";

        handler.getOpenSettlement();

        verify(restService).get(eq(uri), any(QueryParams.class), eq(false), any(TypeReference.class));
    }

    @Test
    void getSettlementPayments() throws Exception {
        String uri = "/settlements/settlement_id/payments";

        handler.getSettlementPayments("settlement_id");

        verify(restService).get(eq(uri), any(QueryParams.class), eq(false), any(TypeReference.class));
    }

    @Test
    void getSettlementRefund() throws Exception {
        String uri = "/settlements/settlement_id/refunds";

        handler.getSettlementRefund("settlement_id");

        verify(restService).get(eq(uri), any(QueryParams.class), eq(false), any(TypeReference.class));
    }

    @Test
    void getSettlementChargebacks() throws Exception {
        String uri = "/settlements/settlement_id/chargebacks";

        handler.getSettlementChargebacks("settlement_id");

        verify(restService).get(eq(uri), any(QueryParams.class), eq(false), any(TypeReference.class));
    }

    @Test
    void getSettlementCaptures() throws Exception {
        String uri = "/settlements/settlement_id/captures";

        handler.getSettlementCaptures("settlement_id");

        verify(restService).get(eq(uri), any(QueryParams.class), eq(false), any(TypeReference.class));
    }
}