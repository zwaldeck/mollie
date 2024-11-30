package be.woutschoovaerts.mollie.handler.client;

import be.woutschoovaerts.mollie.data.client.link.ClientLinkRequest;
import be.woutschoovaerts.mollie.data.client.link.ClientLinkResponse;
import be.woutschoovaerts.mollie.exception.MollieException;
import be.woutschoovaerts.mollie.util.QueryParams;
import be.woutschoovaerts.mollie.util.RestService;
import com.fasterxml.jackson.core.type.TypeReference;
import kong.unirest.UnirestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Handles the Client links API <a href="https://docs.mollie.com/reference/create-client-link">Mollie docs</a>
 *
 * @author Wout Schoovaerts
 */
@Slf4j
@RequiredArgsConstructor
public class ClientLinkHandler {

    private static final TypeReference<ClientLinkResponse> CLIENT_LINK_RESPONSE_TYPE = new TypeReference<>() {
    };

    private final RestService restService;

    /**
     * Link a new or existing organization to your OAuth application, in effect creating a new client. The response contains a clientLink where you should redirect your customer to.
     *
     * @param request The client link request body
     * @return ClientLinkResponse object
     * @throws MollieException something went wrong
     */
    public ClientLinkResponse createClientLink(ClientLinkRequest request) throws MollieException {
        return createClientLink(request, new QueryParams());
    }

    /**
     * Link a new or existing organization to your OAuth application, in effect creating a new client. The response contains a clientLink where you should redirect your customer to.
     *
     * @param request The client link request body
     * @param params  The query params
     * @return ClientLinkResponse object
     * @throws MollieException something went wrong
     */
    public ClientLinkResponse createClientLink(ClientLinkRequest request, QueryParams params) throws MollieException {
        try {
            String uri = "/client-links";

            return restService.post(uri, request, params, CLIENT_LINK_RESPONSE_TYPE);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }
}
