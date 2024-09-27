package be.woutschoovaerts.mollie.handler.connect;

import be.woutschoovaerts.mollie.data.connect.ApprovalPrompt;
import be.woutschoovaerts.mollie.data.connect.AuthorizeRequest;
import be.woutschoovaerts.mollie.data.connect.ResponseType;
import be.woutschoovaerts.mollie.util.RestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class OAuthHandlerTest {

    @Mock
    private RestService restService;

    @InjectMocks
    private OAuthHandler handler;

    @Test
    void createAuthorizeUrl() {
        String expected = "https://my.mollie.com/oauth2/authorize?client_id=client_id&redirect_uri=https%3A%2F%2Fz-soft.be&state=state&scope=scope1+scope2&response_type=code&approval_prompt=auto";

        AuthorizeRequest request = AuthorizeRequest.builder()
                .clientId("client_id")
                .redirectUri(Optional.of("https://z-soft.be"))
                .state("state")
                .scope(new String[] {"scope1", "scope2"})
                .responseType(ResponseType.CODE)
                .approvalPrompt(ApprovalPrompt.AUTO)
                .build();

        assertEquals(expected, handler.createAuthorizeUrl(request));
    }
}