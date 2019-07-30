package be.feelio.mollie.handler;

import be.feelio.mollie.exception.MollieException;
import be.feelio.mollie.json.common.Pagination;
import be.feelio.mollie.json.response.MethodListResponse;
import be.feelio.mollie.json.response.MethodResponse;
import be.feelio.mollie.util.ObjectMapperService;
import be.feelio.mollie.util.QueryParams;
import com.fasterxml.jackson.core.type.TypeReference;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Handles the Method API <a href="https://docs.mollie.com/reference/v2/methods-api/list-methods">Mollie docs</a>
 *
 * @author Wout Schoovaerts
 */
public class MethodHandler extends AbstractHandler {

    private static final Logger log = LoggerFactory.getLogger(MethodHandler.class);

    public MethodHandler(String baseUrl) {
        super(baseUrl, log);
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
     * @return The methods paginated
     * @throws MollieException When something goes wrong
     */
    public Pagination<MethodListResponse> listMethods() throws MollieException {
        return listMethods(QueryParams.EMPTY);
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
        try {
            String uri = "/methods";

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<Pagination<MethodListResponse>>() {
                    });
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Retrieve a single method by its ID. Note that if a method is not available on the website profile a status 404 Not found is returned. When the method is not enabled, a status 403 Forbidden is returned.
     *
     * @param methodId the method id
     * @return The method
     * @throws MollieException when something went wrong
     */
    public MethodResponse getMethod(String methodId) throws MollieException {
        return getMethod(methodId, QueryParams.EMPTY);
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
        try {
            String uri = "/methods/" + methodId;

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<MethodResponse>() {
                    });
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }
}
