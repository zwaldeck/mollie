package be.woutschoovaerts.mollie.handler.payments;

import be.woutschoovaerts.mollie.data.chargeback.ChargebackListResponse;
import be.woutschoovaerts.mollie.data.chargeback.ChargebackResponse;
import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.exception.MollieException;
import be.woutschoovaerts.mollie.util.QueryParams;
import be.woutschoovaerts.mollie.util.RestService;
import com.fasterxml.jackson.core.type.TypeReference;
import kong.unirest.UnirestException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Handles the Charge back API <a href="https://docs.mollie.com/reference/v2/chargebacks-api/get-chargeback">Mollie docs</a>
 *
 * @author Wout Schoovaerts
 */
@RequiredArgsConstructor
public class ChargebackHandler {

    private static final Logger log = LoggerFactory.getLogger(ChargebackHandler.class);

    private static final TypeReference<ChargebackResponse> CHARGEBACK_RESPONSE_TYPE = new TypeReference<>() {
    };
    private static final TypeReference<Pagination<ChargebackListResponse>> CHARGEBACK_LIST_RESPONSE_TYPE = new TypeReference<>() {
    };

    private final RestService restService;

    /**
     * Retrieve a single chargeback by its ID. Note the original payment’s ID is needed as well.
     *
     * @param paymentId a payment id
     * @param chargebackId a chargeback id
     * @return ChargebackResponse object
     * @throws MollieException when something went wrong
     */
    public ChargebackResponse getChargeback(String paymentId, String chargebackId) throws MollieException {
        return getChargeback(paymentId, chargebackId, new QueryParams());
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

            return restService.get(uri, params, false, CHARGEBACK_RESPONSE_TYPE);
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
        return listChargebacks(new QueryParams());
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

            return restService.get(uri, params, false, CHARGEBACK_LIST_RESPONSE_TYPE);
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
        return listChargebacks(paymentId, new QueryParams());
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

            return restService.get(uri, params, false, CHARGEBACK_LIST_RESPONSE_TYPE);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }
}
