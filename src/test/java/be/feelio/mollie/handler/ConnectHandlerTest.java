package be.feelio.mollie.handler;

import be.feelio.mollie.Client;
import be.feelio.mollie.ClientBuilder;
import be.feelio.mollie.data.connect.ApprovalPrompt;
import be.feelio.mollie.data.connect.GrantType;
import be.feelio.mollie.data.connect.ResponseType;
import be.feelio.mollie.data.connect.AuthorizeRequest;
import be.feelio.mollie.data.connect.RevokeTokenRequest;
import be.feelio.mollie.data.connect.TokenRequest;
import be.feelio.mollie.exception.MollieException;
import be.feelio.mollie.util.QueryParams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static be.feelio.mollie.IntegrationTestConstants.API_KEY;
import static org.junit.jupiter.api.Assertions.*;

//https://www.mollie.com/oauth2/authorize?client_id=*client_id*&state=random&scope=payments.read&response_type=code&approval_prompt=auto

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

    @Test
    void generateTokens() throws MollieException {
        TokenRequest request = TokenRequest.builder()
                .grantType(GrantType.AUTHORIZATION_CODE)
                .code(Optional.of("auth_DtphSvzURvQsKVQMGbhTmCSpVxH4Dx"))
                .redirectUri("https://github.com/Feel-IO/mollie")
                .build();

        MollieException ex = assertThrows(MollieException.class, () -> client.connect().generateTokens("",
                "", request, QueryParams.EMPTY));

        String expectedMsg = "Error response from mollie";
        assertEquals(expectedMsg, ex.getMessage());
    }

    @Test
    void removeTokens() throws MollieException {
        RevokeTokenRequest request = RevokeTokenRequest.builder()
                .tokenTypeHint(GrantType.AUTHORIZATION_CODE)
                .token("token")
                .build();

        MollieException ex = assertThrows(MollieException.class, () -> client.connect()
                .revokeToken("", "", request, QueryParams.EMPTY));

        String expectedMsg = "Error response from mollie";
        assertEquals(expectedMsg, ex.getMessage());
    }
}