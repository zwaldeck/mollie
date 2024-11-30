package be.woutschoovaerts.mollie.handler.payments;

import be.woutschoovaerts.mollie.data.capture.CaptureListResponse;
import be.woutschoovaerts.mollie.data.capture.CaptureRequest;
import be.woutschoovaerts.mollie.data.capture.CaptureResponse;
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
 * Handles the capture API <a href="https://docs.mollie.com/reference/v2/captures-api/get-capture">Mollie docs</a>
 *
 * @author Wout Schoovaerts
 */
@RequiredArgsConstructor
public class CaptureHandler {

    private static final Logger log = LoggerFactory.getLogger(CaptureHandler.class);

    private static final TypeReference<CaptureResponse> CAPTURE_RESPONSE_TYPE = new TypeReference<>() {
    };
    private static final TypeReference<Pagination<CaptureListResponse>> CAPTURE_LIST_RESPONSE_TYPE = new TypeReference<>() {
    };

    private final RestService restService;

    /**
     * Retrieve a single capture by its ID. Note the original payment’s ID is needed as well.
     * <p>
     * Captures are used for payments that have the authorize-then-capture flow.
     *
     * @param paymentId a payment id
     * @param captureId a capture id
     * @return CaptureResponse object
     * @throws MollieException when something went wrong
     */
    public CaptureResponse getCapture(String paymentId, String captureId) throws MollieException {
        return getCapture(paymentId, captureId, new QueryParams());
    }

    /**
     * Retrieve a single capture by its ID. Note the original payment’s ID is needed as well.
     * <p>
     * Captures are used for payments that have the authorize-then-capture flow.
     *
     * @param paymentId a payment id
     * @param captureId a capture id
     * @param params    A map of query parameters
     * @return CaptureResponse object
     * @throws MollieException when something went wrong
     */
    public CaptureResponse getCapture(String paymentId, String captureId, QueryParams params)
            throws MollieException {
        try {
            String uri = "/payments/" + paymentId + "/captures/" + captureId;

            return restService.get(uri, params, true, CAPTURE_RESPONSE_TYPE);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Retrieve all captures for a certain payment.
     * <p>
     * Captures are used for payments that have the authorize-then-capture flow.
     *
     * @param paymentId a payment id
     * @return paginated list of CaptureResponse objects
     * @throws MollieException when something went wrong
     */
    public Pagination<CaptureListResponse> listCaptures(String paymentId) throws MollieException {
        return listCaptures(paymentId, new QueryParams());
    }

    /**
     * Retrieve all captures for a certain payment.
     * <p>
     * Captures are used for payments that have the authorize-then-capture flow.
     *
     * @param paymentId a payment id
     * @param params    A map of query parameters
     * @return paginated list of CaptureResponse objects
     * @throws MollieException when something went wrong
     */
    public Pagination<CaptureListResponse> listCaptures(String paymentId, QueryParams params) throws MollieException {
        try {
            String uri = "/payments/" + paymentId + "/captures";

            return restService.get(uri, params, true, CAPTURE_LIST_RESPONSE_TYPE);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Capture an authorized payment.
     * <p>
     * Some payment methods allow you to first collect a customer's authorization, and capture the amount at a later point.
     * <p>
     * By default, Mollie captures payments automatically. If however you configured your payment with captureMode: manual, you can capture the payment using this endpoint after having collected the customer's authorization.
     *
     * @param paymentId a payment id
     * @param request   Request body
     * @return paginated list of CaptureResponse objects
     * @throws MollieException when something went wrong
     */
    public CaptureResponse createCapture(String paymentId, CaptureRequest request) throws MollieException {
        return createCapture(paymentId, request, new QueryParams());
    }

    /**
     * Capture an authorized payment.
     * <p>
     * Some payment methods allow you to first collect a customer's authorization, and capture the amount at a later point.
     * <p>
     * By default, Mollie captures payments automatically. If however you configured your payment with captureMode: manual, you can capture the payment using this endpoint after having collected the customer's authorization.
     *
     * @param paymentId a payment id
     * @param request   Request body
     * @param params    A map of query parameters
     * @return paginated list of CaptureResponse objects
     * @throws MollieException when something went wrong
     */
    public CaptureResponse createCapture(String paymentId, CaptureRequest request, QueryParams params) throws MollieException {
        try {
            String uri = "/payments/" + paymentId + "/captures";

            return restService.post(uri, request, params, CAPTURE_RESPONSE_TYPE);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }
}
