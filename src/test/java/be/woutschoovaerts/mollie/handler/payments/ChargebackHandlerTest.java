package be.woutschoovaerts.mollie.handler.payments;

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
class ChargebackHandlerTest {

    @Mock
    private RestService restService;

    @InjectMocks
    private ChargebackHandler handler;

    @Test
    void getChargeback() throws Exception {
        String uri = "/payments/payment_id/chargebacks/chargeback_id";

        handler.getChargeback("payment_id", "chargeback_id");

        verify(restService).get(eq(uri), any(QueryParams.class), eq(false), any(TypeReference.class));
    }

    @Test
    void listChargebacks_payment() throws Exception {
        String uri = "/payments/payment_id/chargebacks";

        handler.listChargebacks("payment_id");

        verify(restService).get(eq(uri), any(QueryParams.class), eq(false), any(TypeReference.class));
    }

    @Test
    void listChargebacks() throws Exception {
        String uri = "/chargebacks";

        handler.listChargebacks();

        verify(restService).get(eq(uri), any(QueryParams.class), eq(false), any(TypeReference.class));
    }
}