package be.woutschoovaerts.mollie.handler.connect;

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
class OnboardingHandlerTest {

    @Mock
    private RestService restService;

    @InjectMocks
    private OnboardingHandler handler;

    @Test
    void getOnboardingStatus() throws Exception {
        String uri = "/onboarding/me";

        handler.getOnboardingStatus();

        verify(restService).get(eq(uri), any(QueryParams.class), eq(false), any(TypeReference.class));
    }
}