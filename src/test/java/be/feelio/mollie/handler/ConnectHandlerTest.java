package be.feelio.mollie.handler;

import be.feelio.mollie.Client;
import be.feelio.mollie.ClientBuilder;
import be.feelio.mollie.data.enums.ApprovalPrompt;
import be.feelio.mollie.data.enums.ResponseType;
import be.feelio.mollie.data.request.AuthorizeRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static be.feelio.mollie.handler.IntegrationTestConstants.API_KEY;
import static org.junit.jupiter.api.Assertions.*;

class ConnectHandlerTest {

    private Client client;

    @BeforeEach
    void setup() {
        client = new ClientBuilder().withApiKey(API_KEY).build();
    }

    @Test
    void createAuthorizeUrl_withRedirectUri() {
        AuthorizeRequest request = AuthorizeRequest.builder()
                .clientId("client_123")
                .redirectUri(Optional.of("https://google.be"))
                .state("ABC")
                .scope(new String[]{"scope1", "scope2"})
                .responseType(ResponseType.CODE)
                .approvalPrompt(ApprovalPrompt.AUTO)
                .build();

        String expected = "https://www.mollie.com/oauth2/authorize?client_id=client_123" +
                "&redirect_uri=https%3A%2F%2Fgoogle.be&state=ABC&scope=scope1+scope2" +
                "&response_type=name&approval_prompt=auto";

        assertEquals(expected, client.connect().createAuthorizeUrl(request));
    }

    @Test
    void createAuthorizeUrl_withoutRedirectUri() {
        AuthorizeRequest request = AuthorizeRequest.builder()
                .clientId("client_123")
                .state("ABC")
                .scope(new String[]{"scope1", "scope2"})
                .responseType(ResponseType.CODE)
                .approvalPrompt(ApprovalPrompt.AUTO)
                .build();

        String expected = "https://www.mollie.com/oauth2/authorize?client_id=client_123" +
                "&state=ABC&scope=scope1+scope2&response_type=name&approval_prompt=auto";

        assertEquals(expected, client.connect().createAuthorizeUrl(request));
    }
}