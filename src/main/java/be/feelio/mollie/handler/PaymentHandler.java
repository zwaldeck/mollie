package be.feelio.mollie.handler;

import be.feelio.mollie.exception.MollieException;
import be.feelio.mollie.json.common.Pagination;
import be.feelio.mollie.json.request.PaymentRequest;
import be.feelio.mollie.json.response.PaymentListResponse;
import be.feelio.mollie.json.response.PaymentResponse;
import be.feelio.mollie.util.ObjectMapperService;
import be.feelio.mollie.util.QueryParams;
import com.fasterxml.jackson.core.type.TypeReference;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

// TODO support Mollie Connect/OAuth parameters
// TODO support QR codes

/**
 * Handles the Payments API <a href="https://docs.mollie.com/reference/v2/payments-api/create-payment">Mollie docs</a>
 *
 * @author Wout Schoovaerts
 */
public class PaymentHandler extends AbstractHandler {

    private static final Logger log = LoggerFactory.getLogger(PaymentHandler.class);

    public PaymentHandler(String baseUrl) {
        super(baseUrl, log);

    }

    /**
     * Payment creation is elemental to the Mollie API: this is where most payment implementations start off.
     *
     * @param body PaymentRequest can be build with the builder pattern
     * @return The payment response from mollie
     * @throws MollieException when there went something wrong
     */
    public PaymentResponse createPayment(PaymentRequest body) throws MollieException {
        return createPayment(body, QueryParams.EMPTY);
    }

    /**
     * Payment creation is elemental to the Mollie API: this is where most payment implementations start off.
     *
     * @param body   PaymentRequest can be build with the builder pattern
     * @param params A map of query parameters
     * @return The payment response from mollie
     * @throws MollieException when there went something wrong
     */
    public PaymentResponse createPayment(PaymentRequest body, QueryParams params) throws MollieException {
        try {
            String uri = "/payments";

            HttpResponse<String> response = post(uri, body, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), PaymentResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Retrieve a single payment object by its payment token.
     *
     * @param paymentId payment token
     * @return The payment response from mollie
     * @throws MollieException when there went something wrong
     */
    public PaymentResponse getPayment(String paymentId) throws MollieException {
        return getPayment(paymentId, QueryParams.EMPTY);
    }

    /**
     * Retrieve a single payment object by its payment token.
     *
     * @param paymentId payment token
     * @param params    A map of query parameters
     * @return The payment response from mollie
     * @throws MollieException when there went something wrong
     */
    public PaymentResponse getPayment(String paymentId, QueryParams params)
            throws MollieException {
        try {
            String uri = "/payments/" + paymentId;

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), PaymentResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Some payment methods are cancellable for an amount of time, usually until the next day.
     * Or as long as the payment status is open.
     * Payments may be canceled manually from the Dashboard, or automatically by using this endpoint.
     * <p>
     * The isCancelable property on the Payment object will indicate if the payment can be canceled.
     *
     * @param paymentId payment token
     * @return The payment response from mollie
     * @throws MollieException when there went something wrong
     */
    public PaymentResponse cancelPayment(String paymentId) throws MollieException {
        return cancelPayment(paymentId, QueryParams.EMPTY);
    }

    /**
     * Some payment methods are cancellable for an amount of time, usually until the next day.
     * Or as long as the payment status is open.
     * Payments may be canceled manually from the Dashboard, or automatically by using this endpoint.
     * <p>
     * The isCancelable property on the Payment object will indicate if the payment can be canceled.
     *
     * @param paymentId payment token
     * @param params    A map of query parameters
     * @return The payment response from mollie
     * @throws MollieException when there went something wrong
     */
    public PaymentResponse cancelPayment(String paymentId, QueryParams params) throws MollieException {
        try {
            String uri = "/payments/" + paymentId;

            HttpResponse<String> response = delete(uri, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), PaymentResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Retrieve all payments created with the current website profile, ordered from newest to oldest.
     * <p>
     * The results are paginated.
     *
     * @return paginated payment response list
     * @throws MollieException when there went something wrong
     */
    public Pagination<PaymentListResponse> listPayments() throws MollieException {
        return listPayments(QueryParams.EMPTY);
    }


    /**
     * Retrieve all payments created with the current website profile, ordered from newest to oldest.
     * <p>
     * The results are paginated.
     *
     * @param params A map of query parameters
     * @return paginated payment response list
     * @throws MollieException when there went something wrong
     */
    public Pagination<PaymentListResponse> listPayments(QueryParams params) throws MollieException {
        try {
            String uri = "/payments";

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<Pagination<PaymentListResponse>>() {
                    });
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }
}
