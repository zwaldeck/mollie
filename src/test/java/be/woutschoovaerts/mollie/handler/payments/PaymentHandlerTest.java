package be.woutschoovaerts.mollie.handler.payments;

import be.woutschoovaerts.mollie.data.payment.PaymentRequest;
import be.woutschoovaerts.mollie.data.payment.UpdatePaymentRequest;
import be.woutschoovaerts.mollie.util.QueryParams;
import be.woutschoovaerts.mollie.util.RestService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PaymentHandlerTest {
    @Mock
    private RestService restService;

    @InjectMocks
    private PaymentHandler handler;

    @Test
    void createPayment() throws Exception{
        String uri = "/payments";

        PaymentRequest request = PaymentRequest.builder()
                .description("payment description")
                .build();

        handler.createPayment(request);

        verify(restService).post(eq(uri), eq(request), any(QueryParams.class), any(TypeReference.class));
    }

    @Test
    void getPayment() throws Exception {
        String uri = "/payments/payment_id";

        handler.getPayment("payment_id");

        verify(restService).get(eq(uri), any(QueryParams.class), eq(true), any(TypeReference.class));
    }

    @Test
    void updatePayment() throws Exception{
        String uri = "/payments/payment_id";

        UpdatePaymentRequest request = UpdatePaymentRequest.builder()
                .description(Optional.of("payment description"))
                .build();

        handler.updatePayment("payment_id", request);

        verify(restService).patch(eq(uri), eq(request), any(QueryParams.class), any(TypeReference.class));
    }

    @Test
    void cancelPayment() throws Exception {
        String uri = "/payments/payment_id";

        handler.cancelPayment("payment_id");

        verify(restService).delete(eq(uri), any(QueryParams.class), eq(true), any(TypeReference.class));
    }

    @Test
    void listPayments() throws Exception {
        String uri = "/payments";

        handler.listPayments();

        verify(restService).get(eq(uri), any(QueryParams.class), eq(true), any(TypeReference.class));
    }
}