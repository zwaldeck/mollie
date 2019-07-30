package be.feelio.mollie.handler;

import be.feelio.mollie.exception.MollieException;
import be.feelio.mollie.json.common.Pagination;
import be.feelio.mollie.json.response.CaptureListResponse;
import be.feelio.mollie.json.response.CaptureResponse;
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
 * Handles the capture API <a href="https://docs.mollie.com/reference/v2/captures-api/get-capture">Mollie docs</a>
 *
 * @author Wout Schoovaerts
 */
public class CaptureHandler extends AbstractHandler {

    private static final Logger log = LoggerFactory.getLogger(CaptureHandler.class);

    public CaptureHandler(String baseUrl) {
        super(baseUrl, log);
    }

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
        return getCapture(paymentId, captureId, QueryParams.EMPTY);
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
     * Retrieve all captures for a certain payment.
     * <p>
     * Captures are used for payments that have the authorize-then-capture flow.
     *
     * @param paymentId a payment id
     * @return paginated list of CaptureResponse objects
     * @throws MollieException when something went wrong
     */
    public Pagination<CaptureListResponse> listCaptures(String paymentId) throws MollieException {
        return listCaptures(paymentId, QueryParams.EMPTY);
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
