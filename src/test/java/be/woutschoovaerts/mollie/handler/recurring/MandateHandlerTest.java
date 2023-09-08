package be.woutschoovaerts.mollie.handler.recurring;

import be.woutschoovaerts.mollie.data.mandate.MandateRequest;
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
class MandateHandlerTest {

    @Mock
    private RestService restService;

    @InjectMocks
    private MandateHandler handler;

    @Test
    void createMandate() throws Exception {
        String uri = "/customers/customer_id/mandates";

        MandateRequest request = MandateRequest.builder()
                .consumerName("mandate consumer name")
                .build();

        handler.createMandate("customer_id", request);

        verify(restService).post(eq(uri), eq(request), any(QueryParams.class), any(TypeReference.class));
    }

    @Test
    void getMandate() throws Exception {
        String uri = "/customers/customer_id/mandates/mandate_id";

        handler.getMandate("customer_id", "mandate_id");

        verify(restService).get(eq(uri), any(QueryParams.class), eq(true), any(TypeReference.class));
    }

    @Test
    void revokeMandate() throws Exception {
        String uri = "/customers/customer_id/mandates/mandate_id";

        handler.revokeMandate("customer_id", "mandate_id");

        verify(restService).delete(eq(uri), any(QueryParams.class), eq(true), any(TypeReference.class));
    }

    @Test
    void listMandates() throws Exception {
        String uri = "/customers/customer_id/mandates";

        handler.listMandates("customer_id");

        verify(restService).get(eq(uri), any(QueryParams.class), eq(true), any(TypeReference.class));
    }
}