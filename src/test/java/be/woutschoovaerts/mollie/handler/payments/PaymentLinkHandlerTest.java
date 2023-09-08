package be.woutschoovaerts.mollie.handler.payments;

import be.woutschoovaerts.mollie.data.paymentLink.PaymentLinkRequest;
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

}