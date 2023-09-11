package be.woutschoovaerts.mollie.handler.connect;

import be.woutschoovaerts.mollie.data.connect.AuthorizeRequest;
import be.woutschoovaerts.mollie.data.connect.RevokeTokenRequest;
import be.woutschoovaerts.mollie.data.connect.TokenRequest;
import be.woutschoovaerts.mollie.data.connect.TokenResponse;
import be.woutschoovaerts.mollie.exception.MollieException;
import be.woutschoovaerts.mollie.util.QueryParams;
import be.woutschoovaerts.mollie.util.RestService;
import be.woutschoovaerts.mollie.util.UrlUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;

import java.net.URI;

/**
 * Handles the Connect API <a href="https://docs.mollie.com/reference/oauth2/authorize">Mollie docs</a>
 *
 * @author Wout Schoovaerts
 */
@RequiredArgsConstructor
public class OAuthHandler {

    private static final TypeReference<TokenResponse> TOKEN_RESPONSE_TYPE_REFERENCE = new TypeReference<>() {};
    private static final TypeReference<Void> VOID_TYPE_REFERENCE = new TypeReference<>() {};
    private static final String API_MOLLIE_COM_OAUTH_2_TOKENS = "https://api.mollie.com/oauth2/tokens";

    private final RestService restService;

    /**
     * The Authorize endpoint is the endpoint on Mollie web site where the merchant logs in, and grants authorization to your client application. E.g. when the merchant clicks on the Connect with Mollie button, you should redirect the merchant to the Authorize endpoint.
     * <p>
     * The resource owner can then grant the authorization to your client application for the scopes you have requested.
     * <p>
     * Mollie will then redirect the resource owner to the redirect_uri you have specified. The redirect_uri will be appended with a code parameter, which will contain the auth token. You should then exchange the auth token for an access token using the Tokens API.
     *
     * @param request AuthorizeRequest object
     * @return The authorize URL
     */
    public String createAuthorizeUrl(AuthorizeRequest request) {
        return "https://www.mollie.com/oauth2/authorize" + convertAuthorizeRequestToQueryParams(request);
    }

    /**
     * Exchange the auth code received at the Authorize endpoint for an actual access token, with which you can communicate with the Mollie API.
     *
     * @param clientId     The OAuth client ID
     * @param clientSecret The OAuth client ID
     * @param body         TokenRequest object
     * @return TokenResponse object
     * @throws MollieException when something went wrong
     */
    public TokenResponse generateTokens(String clientId, String clientSecret, TokenRequest body)
            throws MollieException {
        return generateTokens(clientId, clientSecret, body, new QueryParams());
    }

    /**
     * Exchange the auth code received at the Authorize endpoint for an actual access token, with which you can communicate with the Mollie API.
     *
     * @param clientId     The OAuth client ID
     * @param clientSecret The OAuth client ID
     * @param body         TokenRequest object
     * @param params       A map of query parameters
     * @return TokenResponse object
     * @throws MollieException when something went wrong
     */
    public TokenResponse generateTokens(String clientId, String clientSecret, TokenRequest body, QueryParams params)
            throws MollieException {
        URI url = URI.create(API_MOLLIE_COM_OAUTH_2_TOKENS + params);
        return restService.post(url, clientId, clientSecret, getGenerateTokensBody(body), TOKEN_RESPONSE_TYPE_REFERENCE);
    }

    /**
     * Revoke an access- or a refresh token. Once revoked the token can not be used anymore.
     *
     * @param clientId     The OAuth client ID
     * @param clientSecret The OAuth client ID
     * @param body         RevokeTokenRequest object
     * @throws MollieException when something went wrong
     */
    public void revokeToken(String clientId, String clientSecret, RevokeTokenRequest body) throws MollieException {
        revokeToken(clientId, clientSecret, body, new QueryParams());
    }

    /**
     * Revoke an access- or a refresh token. Once revoked the token can not be used anymore.
     *
     * @param clientId     The OAuth client ID
     * @param clientSecret The OAuth client ID
     * @param body         RevokeTokenRequest object
     * @param params       A map of query parameters
     * @throws MollieException when something went wrong
     */
    public void revokeToken(String clientId, String clientSecret, RevokeTokenRequest body, QueryParams params)
            throws MollieException {
        URI url = URI.create(API_MOLLIE_COM_OAUTH_2_TOKENS + params);
        restService.delete(url, clientId, clientSecret, getRevokeTokenBody(body), VOID_TYPE_REFERENCE);
    }

    private QueryParams convertAuthorizeRequestToQueryParams(AuthorizeRequest request) {
        QueryParams params = new QueryParams();

        params.put("client_id", request.getClientId());
        request.getRedirectUri().ifPresent(uri -> params.put("redirect_uri", uri));
        params.put("state", request.getState());
        params.put("scope", String.join(" ", request.getScope()));
        params.put("response_type", request.getResponseType().getValue());
        params.put("approval_prompt", request.getApprovalPrompt().getValue());

        return params;
    }

    private String getGenerateTokensBody(TokenRequest request) {
        StringBuilder sb = new StringBuilder();
        sb.append("grant_type=").append(UrlUtils.urlEncode(request.getGrantType().name().toLowerCase()));
        request.getCode().ifPresent(code -> sb.append("&code=").append(UrlUtils.urlEncode(code)));
        request.getRefreshToken().ifPresent(token -> sb.append("&refresh_token=").append(UrlUtils.urlEncode(token)));
        request.getRedirectUri().ifPresent(uri -> sb.append("&redirect_uri=").append(UrlUtils.urlEncode(uri)));

        return sb.toString();
    }

    private String getRevokeTokenBody(RevokeTokenRequest request) {
        return "token_type_hint=" + UrlUtils.urlEncode(request.getTokenTypeHint().name().toLowerCase()) +
                "&token=" + UrlUtils.urlEncode(request.getToken());
    }
}
