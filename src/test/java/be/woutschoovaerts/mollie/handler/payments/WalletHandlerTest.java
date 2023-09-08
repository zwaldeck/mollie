package be.woutschoovaerts.mollie.handler.payments;

import be.woutschoovaerts.mollie.data.wallet.ApplePaySessionRequest;
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
class WalletHandlerTest {

    @Mock
    private RestService restService;

    @InjectMocks
    private WalletHandler handler;

    @Test
    void requestApplePaySession() throws Exception{
        String uri = "/wallets/applepay/sessions";

        ApplePaySessionRequest request = ApplePaySessionRequest.builder()
                .domain("https://z-soft.be")
                .build();

        handler.requestApplePaySession(request);

        verify(restService).post(eq(uri), eq(request), any(TypeReference.class));
    }
}