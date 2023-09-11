package be.woutschoovaerts.mollie.handler.client;

import be.woutschoovaerts.mollie.data.client.ClientResponse;
import be.woutschoovaerts.mollie.data.client.ClientsListResponse;
import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.exception.MollieException;
import be.woutschoovaerts.mollie.util.QueryParams;
import be.woutschoovaerts.mollie.util.RestService;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;

/**
 * Handles the Clients API <a href="https://docs.mollie.com/reference/v2/clients-api/get-client">Mollie docs</a>
 *
 * @author Wout Schoovaerts
 */
@RequiredArgsConstructor
public class ClientHandler {

    private static final TypeReference<Pagination<ClientsListResponse>> CLIENTS_LIST_RESPONSE_TYPE = new TypeReference<Pagination<ClientsListResponse>>() {
    };
    private static final TypeReference<ClientResponse> CLIENT_RESPONSE_TYPE = new TypeReference<ClientResponse>() {
    };


    private final RestService restService;

    /**
     * Retrieve all clients linked to your partner account.
     *
     * @return Pagination<ClientsListResponse> object
     * @throws MollieException something went wrong
     */
    public Pagination<ClientsListResponse> getClients() throws MollieException {
        return getClients(new QueryParams());
    }

    /**
     * Retrieve all clients linked to your partner account.
     *
     * @param params The Query params
     * @return Pagination<ClientsListResponse> object
     * @throws MollieException something went wrong
     */
    public Pagination<ClientsListResponse> getClients(QueryParams params) throws MollieException {
            String uri = "/clients";

            return restService.get(uri, params, false, CLIENTS_LIST_RESPONSE_TYPE);
    }

    /**
     * Retrieve a single client, linked to your partner account, by its ID.
     *
     * @param clientId The client ID
     * @return ClientResponse object
     * @throws MollieException something went wrong
     */
    public ClientResponse getClient(String clientId) throws MollieException {
        return getClient(clientId, new QueryParams());
    }

    /**
     * Retrieve a single client, linked to your partner account, by its ID.
     *
     * @param clientId The client ID
     * @param params The query params
     * @return ClientResponse object
     * @throws MollieException something went wrong
     */
    public ClientResponse getClient(String clientId, QueryParams params) throws MollieException {
            String uri = "/clients/" + clientId;

            return restService.get(uri, params, false, CLIENT_RESPONSE_TYPE);
    }
}
