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
class MethodHandlerTest {

    @Mock
    private RestService restService;

    @InjectMocks
    private MethodHandler handler;

    @Test
    void listMethods() throws Exception {
        String uri = "/methods";

        handler.listMethods();

        verify(restService).get(eq(uri), any(QueryParams.class), eq(true), any(TypeReference.class));
    }

    @Test
    void listAllMethods() throws Exception {
        String uri = "/methods/all";

        handler.listAllMethods();

        verify(restService).get(eq(uri), any(QueryParams.class), eq(false), any(TypeReference.class));
    }

    @Test
    void getMethod() throws Exception {
        String uri = "/methods/method_id";

        handler.getMethod("method_id");

        verify(restService).get(eq(uri), any(QueryParams.class), eq(true), any(TypeReference.class));
    }
}