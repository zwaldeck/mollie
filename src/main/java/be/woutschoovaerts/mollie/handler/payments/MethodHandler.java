package be.woutschoovaerts.mollie.handler.payments;

import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.data.method.MethodListResponse;
import be.woutschoovaerts.mollie.data.method.MethodResponse;
import be.woutschoovaerts.mollie.exception.MollieException;
import be.woutschoovaerts.mollie.util.QueryParams;
import be.woutschoovaerts.mollie.util.RestService;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;

/**
 * Handles the Method API <a href="https://docs.mollie.com/reference/v2/methods-api/list-methods">Mollie docs</a>
 *
 * @author Wout Schoovaerts
 */
@RequiredArgsConstructor
public class MethodHandler {

    private static final TypeReference<Pagination<MethodListResponse>> METHOD_LIST_RESPONSE_TYPE = new TypeReference<>() {
    };
    private static final TypeReference<MethodResponse> METHOD_RESPONSE_TYPE = new TypeReference<>() {
    };


    private final RestService restService;


    /**
     * Retrieve all available payment methods. The results are not paginated.
     *
     * For test mode, payment methods are returned that are enabled in the Dashboard (or the activation is pending).
     * For live mode, payment methods are returned that have been activated on your account and have been enabled in the Dashboard.
     * When using the first sequence type, methods will be returned if they can be used as a first payment in a recurring sequence and if they are enabled in the Dashboard.
     *
     * When using the recurring sequence type, payment methods that can be used for recurring payments or subscriptions will be returned. Enabling / disabling methods in the dashboard does not affect how they can be used for recurring payments.
     *
     * @return The methods paginated
     * @throws MollieException When something goes wrong
     */
    public Pagination<MethodListResponse> listMethods() throws MollieException {
        return listMethods(new QueryParams());
    }

    /**
     * Retrieve all available payment methods. The results are not paginated.
     *
     * For test mode, payment methods are returned that are enabled in the Dashboard (or the activation is pending).
     * For live mode, payment methods are returned that have been activated on your account and have been enabled in the Dashboard.
     * When using the first sequence type, methods will be returned if they can be used as a first payment in a recurring sequence and if they are enabled in the Dashboard.
     *
     * When using the recurring sequence type, payment methods that can be used for recurring payments or subscriptions will be returned. Enabling / disabling methods in the dashboard does not affect how they can be used for recurring payments.
     *
     * @param params A map of query parameters
     * @return The methods paginated
     * @throws MollieException When something goes wrong
     */
    public Pagination<MethodListResponse> listMethods(QueryParams params) throws MollieException {
            String uri = "/methods";

            return restService.get(uri, params, true, METHOD_LIST_RESPONSE_TYPE);
    }

    /**
     * Retrieve all payment methods that Mollie offers and can be activated by the Organization.
     * The results are not paginated. New payment methods can be activated via the Enable payment method endpoint in the Profiles API.
     *
     * @return The methods paginated
     * @throws MollieException When something goes wrong
     */
    public Pagination<MethodListResponse> listAllMethods() throws MollieException {
        return listAllMethods(new QueryParams());
    }

    /**
     * Retrieve all payment methods that Mollie offers and can be activated by the Organization.
     * The results are not paginated. New payment methods can be activated via the Enable payment method endpoint in the Profiles API.
     *
     * @param params A map of query parameters
     * @return The methods paginated
     * @throws MollieException When something goes wrong
     */
    public Pagination<MethodListResponse> listAllMethods(QueryParams params) throws MollieException {
            String uri = "/methods/all";

            return restService.get(uri, params, false, METHOD_LIST_RESPONSE_TYPE);
    }

    /**
     * Retrieve a single method by its ID. Note that if a method is not available on the website profile a status 404 Not found is returned. When the method is not enabled, a status 403 Forbidden is returned.
     *
     * @param methodId the method id
     * @return The method
     * @throws MollieException when something went wrong
     */
    public MethodResponse getMethod(String methodId) throws MollieException {
        return getMethod(methodId, new QueryParams());
    }

    /**
     * Retrieve a single method by its ID. Note that if a method is not available on the website profile a status 404 Not found is returned. When the method is not enabled, a status 403 Forbidden is returned.
     *
     * @param methodId the method id
     * @param params A map of query parameters
     * @return The method
     * @throws MollieException when something went wrong
     */
    public MethodResponse getMethod(String methodId, QueryParams params) throws MollieException {
            String uri = "/methods/" + methodId;

            return restService.get(uri, params, true, METHOD_RESPONSE_TYPE);
    }
}
