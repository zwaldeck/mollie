package be.woutschoovaerts.mollie.handler.payments;

import be.woutschoovaerts.mollie.data.common.Amount;
import be.woutschoovaerts.mollie.data.refund.RefundRequest;
import be.woutschoovaerts.mollie.util.QueryParams;
import be.woutschoovaerts.mollie.util.RestService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RefundHandlerTest {

    @Mock
    private RestService restService;

    @InjectMocks
    private RefundHandler handler;

    @Test
    void createRefund() throws Exception {
        String uri = "/payments/payment_id/refunds";

        RefundRequest request = RefundRequest.builder()
                .amount(Amount.builder().currency("EUR").value(new BigDecimal("10.00")).build())
                .build();

        handler.createRefund("payment_id", request);

        verify(restService).post(eq(uri), eq(request), any(QueryParams.class), any(TypeReference.class));
    }

    @Test
    void getRefund() throws Exception {
        String uri = "/payments/payment_id/refunds/refund_id";

        handler.getRefund("payment_id", "refund_id");

        verify(restService).get(eq(uri), any(QueryParams.class), eq(true), any(TypeReference.class));
    }

    @Test
    void cancelRefund() throws Exception {
        String uri = "/payments/payment_id/refunds/refund_id";

        handler.cancelRefund("payment_id", "refund_id");

        verify(restService).delete(eq(uri), any(QueryParams.class), eq(true), any(TypeReference.class));
    }

    @Test
    void listRefunds() throws Exception {
        String uri = "/refunds";

        handler.listRefunds();

        verify(restService).get(eq(uri), any(QueryParams.class), eq(true), any(TypeReference.class));
    }

    @Test
    void listRefunds_payments() throws Exception {
        String uri = "/payments/payment_id/refunds";

        handler.listRefunds("payment_id");

        verify(restService).get(eq(uri), any(QueryParams.class), eq(true), any(TypeReference.class));
    }
}