package be.feelio.mollie.handler;

import be.feelio.mollie.data.request.AuthorizeRequest;
import be.feelio.mollie.data.request.TokenRequest;
import be.feelio.mollie.data.response.TokenResponse;
import be.feelio.mollie.exception.MollieException;
import be.feelio.mollie.util.ObjectMapperService;
import be.feelio.mollie.util.QueryParams;
import be.feelio.mollie.util.UrlUtils;
import kong.unirest.HttpRequest;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ConnectHandler extends AbstractHandler {

    private static final Logger log = LoggerFactory.getLogger(ConnectHandler.class);

    public ConnectHandler() {
        super(null, log);
    }

    public String createAuthorizeUrl(AuthorizeRequest request) {
        return "https://www.mollie.com/oauth2/authorize" + convertAuthorizeRequestToQueryParams(request).toString();
    }

    public TokenResponse generateTokens(String clientId, String clientSecret, TokenRequest body, QueryParams params) throws MollieException {
        try {
            String url = "https://api.mollie.com/oauth2/tokens" + params.toString();

            log.info("Executing 'POST {}'", url);
            HttpResponse<String> response = Unirest.post(url)
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .basicAuth(clientId, clientSecret)
                    .body(getGenerateTokensBody(body))
                    .asString();


            validateResponse(response);
            log.info("Successful response 'POST {}'", url);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), TokenResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
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
        sb.append("&redirect_uri=").append(UrlUtils.urlEncode(request.getRedirectUri()));

        return sb.toString();
    }
}
