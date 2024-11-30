package be.woutschoovaerts.mollie.handler.payments;

import be.woutschoovaerts.mollie.data.paymentLink.PaymentLinkRequest;
import be.woutschoovaerts.mollie.data.paymentLink.UpdatePaymentLinkRequest;
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
class PaymentLinkHandlerTest {

    @Mock
    private RestService restService;

    @InjectMocks
    private PaymentLinkHandler handler;

    @Test
    void createPaymentLink() throws Exception {
        String uri = "/payment-links";

        PaymentLinkRequest request = PaymentLinkRequest.builder()
                .description("payment link description")
                .build();

        handler.createPaymentLink(request);

        verify(restService).post(eq(uri), eq(request), any(QueryParams.class), any(TypeReference.class));
    }

    @Test
    void getPaymentLink() throws Exception {
        String uri = "/payment-links/link_id";

        handler.getPaymentLink("link_id");

        verify(restService).get(eq(uri), any(QueryParams.class), eq(true), any(TypeReference.class));
    }

    @Test
    void listPaymentLinks() throws Exception {
        String uri = "/payment-links";

        handler.listPaymentLinks();

        verify(restService).get(eq(uri), any(QueryParams.class), eq(true), any(TypeReference.class));
    }


    @Test
    void updatePaymentLink() throws Exception {
        UpdatePaymentLinkRequest request = UpdatePaymentLinkRequest.builder()
                .archived(false)
                .build();
        String uri = "/payment-links/payment_link_id";

        handler.updatePaymentLink("payment_link_id", request);

        verify(restService).patch(eq(uri), eq(request), any(QueryParams.class), any(TypeReference.class));
    }

    @Test
    void deletePaymentLink() throws Exception {
        String uri = "/payment-links/payment_link_id";

        handler.deletePaymentLink("payment_link_id");

        verify(restService).delete(eq(uri), any(QueryParams.class), any(TypeReference.class));
    }

    @Test
    void listPaymentLinkPayments() throws Exception {
        String uri = "/payment-links/payment_link_id/payments";

        handler.listPaymentLinkPayments("payment_link_id");

        verify(restService).get(eq(uri), any(QueryParams.class), eq(true), any(TypeReference.class));
    }

}