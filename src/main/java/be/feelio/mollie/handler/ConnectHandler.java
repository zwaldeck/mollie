package be.feelio.mollie.handler;

import be.feelio.mollie.data.request.AuthorizeRequest;
import be.feelio.mollie.util.QueryParams;

public class ConnectHandler {

    public String createAuthorizeUrl(AuthorizeRequest request) {
        return "https://www.mollie.com/oauth2/authorize" + convertAuthorizeRequestToQueryParams(request).toString();
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
}
