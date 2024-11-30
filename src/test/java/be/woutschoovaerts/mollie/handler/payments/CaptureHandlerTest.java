package be.woutschoovaerts.mollie.handler.payments;

import be.woutschoovaerts.mollie.data.capture.CaptureRequest;
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
class CaptureHandlerTest {

    @Mock
    private RestService restService;

    @InjectMocks
    private CaptureHandler handler;

    @Test
    void getCapture() throws Exception {
        String uri = "/payments/payment_id/captures/capture_id";

        handler.getCapture("payment_id", "capture_id");

        verify(restService).get(eq(uri), any(QueryParams.class), eq(true), any(TypeReference.class));
    }

    @Test
    void listCaptures() throws Exception {
        String uri = "/payments/payment_id/captures";

        handler.listCaptures("payment_id");

        verify(restService).get(eq(uri), any(QueryParams.class), eq(true), any(TypeReference.class));
    }

    @Test
    void createCapture() throws Exception {
        CaptureRequest request = CaptureRequest.builder()
                .description(Optional.of("Create capture description"))
                .build();
        String uri = "/payments/payment_id/captures";

        handler.createCapture("payment_id", request);

        verify(restService).post(eq(uri), eq(request), any(QueryParams.class), any(TypeReference.class));
    }

}