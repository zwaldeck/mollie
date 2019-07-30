package be.feelio.mollie.handler;

import be.feelio.mollie.exception.MollieException;
import be.feelio.mollie.json.common.Pagination;
import be.feelio.mollie.json.response.ChargebackListResponse;
import be.feelio.mollie.json.response.ChargebackResponse;
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
 * Handles the Charge back API <a href="https://docs.mollie.com/reference/v2/chargebacks-api/get-chargeback">Mollie docs</a>
 *
 * @author Wout Schoovaerts
 */
public class ChargebackHandler extends AbstractHandler {

    private static final Logger log = LoggerFactory.getLogger(ChargebackHandler.class);

    public ChargebackHandler(String baseUrl) {
        super(baseUrl, log);
    }

    /**
     * Retrieve a single chargeback by its ID. Note the original payment’s ID is needed as well.
     *
     * @param paymentId a payment id
     * @param chargebackId a chargeback id
     * @return ChargebackResponse object
     * @throws MollieException when something went wrong
     */
    public ChargebackResponse getChargeback(String paymentId, String chargebackId) throws MollieException {
        return getChargeback(paymentId, chargebackId, QueryParams.EMPTY);
    }

    /**
     * Retrieve a single chargeback by its ID. Note the original payment’s ID is needed as well.
     *
     * @param paymentId a payment id
     * @param chargebackId a chargeback id
     * @param params A map of query parameters
     * @return ChargebackResponse object
     * @throws MollieException when something went wrong
     */
    public ChargebackResponse getChargeback(String paymentId, String chargebackId, QueryParams params)
            throws MollieException {
        try {
            String uri = "/payments/" + paymentId + "/chargebacks/" + chargebackId;

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<ChargebackResponse>() {
                    });
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Retrieve all received chargebacks. If the payment-specific endpoint is used, only chargebacks for that specific payment are returned.
     *
     * The results are not paginated.
     *
     * @return paginated response of ChagebackResponse objects
     * @throws MollieException when something went wrong
     */
    public Pagination<ChargebackListResponse> listChargebacks() throws MollieException {
        return listChargebacks(QueryParams.EMPTY);
    }

    /**
     * Retrieve all received chargebacks. If the payment-specific endpoint is used, only chargebacks for that specific payment are returned.
     *
     * The results are not paginated.
     *
     * @param params A map of query parameters
     * @return paginated response of ChagebackResponse objects
     * @throws MollieException when something went wrong
     */
    public Pagination<ChargebackListResponse> listChargebacks(QueryParams params) throws MollieException {
        try {
            String uri = "/chargebacks";

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<Pagination<ChargebackListResponse>>() {
                    });
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Retrieve all received chargebacks. If the payment-specific endpoint is used, only chargebacks for that specific payment are returned.
     *
     * The results are not paginated.
     *
     * @param paymentId A payment id
     * @return paginated response of ChagebackResponse objects
     * @throws MollieException when something went wrong
     */
    public Pagination<ChargebackListResponse> listChargebacks(String paymentId) throws MollieException {
        return listChargebacks(paymentId, QueryParams.EMPTY);
    }

    /**
     * Retrieve all received chargebacks.
     * only chargebacks for that specific payment are returned.
     * <p>
     * The results are not paginated.
     *
     * @param paymentId A payment id
     * @param params A map of query parameters
     * @return paginated response of ChagebackResponse objects
     * @throws MollieException when something went wrong
     */
    public Pagination<ChargebackListResponse> listChargebacks(String paymentId, QueryParams params)
            throws MollieException {
        try {
            String uri = "/payments/" + paymentId + "/chargebacks";

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<Pagination<ChargebackListResponse>>() {
                    });
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }
}
