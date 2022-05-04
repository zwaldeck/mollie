package be.woutschoovaerts.mollie.handler;

import be.woutschoovaerts.mollie.Client;
import be.woutschoovaerts.mollie.ClientBuilder;
import be.woutschoovaerts.mollie.data.connect.*;
import be.woutschoovaerts.mollie.exception.MollieException;
import be.woutschoovaerts.mollie.util.QueryParams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static be.woutschoovaerts.mollie.IntegrationTestConstants.API_KEY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
                "&response_type=code&approval_prompt=auto";

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
                "&state=ABC&scope=scope1+scope2&response_type=code&approval_prompt=auto";

        assertEquals(expected, client.connect().createAuthorizeUrl(request));
    }

    @Test
    void generateTokens() throws MollieException {
        TokenRequest request = TokenRequest.builder()
                .grantType(GrantType.AUTHORIZATION_CODE)
                .code(Optional.of("auth_DtphSvzURvQsKVQMGbhTmCSpVxH4Dx"))
                .redirectUri(Optional.of("https://github.com/Feel-IO/mollie"))
                .build();

        MollieException ex = assertThrows(MollieException.class, () -> client.connect().generateTokens("",
                "", request, new QueryParams()));

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
                .revokeToken("", "", request, new QueryParams()));

        String expectedMsg = "Error response from mollie";
        assertEquals(expectedMsg, ex.getMessage());
    }
}