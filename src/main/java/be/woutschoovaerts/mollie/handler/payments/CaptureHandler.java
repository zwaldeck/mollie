package be.woutschoovaerts.mollie.handler.payments;

import be.woutschoovaerts.mollie.data.capture.CaptureListResponse;
import be.woutschoovaerts.mollie.data.capture.CaptureResponse;
import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.exception.MollieException;
import be.woutschoovaerts.mollie.util.QueryParams;
import be.woutschoovaerts.mollie.util.RestService;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;

/**
 * Handles the capture API <a href="https://docs.mollie.com/reference/v2/captures-api/get-capture">Mollie docs</a>
 *
 * @author Wout Schoovaerts
 */
@RequiredArgsConstructor
public class CaptureHandler {

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
            String uri = "/payments/" + paymentId + "/captures/" + captureId;

            return restService.get(uri, params, true, CAPTURE_RESPONSE_TYPE);
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
            String uri = "/payments/" + paymentId + "/captures";

            return restService.get(uri, params, true, CAPTURE_LIST_RESPONSE_TYPE);
    }
}
