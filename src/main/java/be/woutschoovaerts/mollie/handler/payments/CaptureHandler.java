package be.woutschoovaerts.mollie.handler.payments;

import be.woutschoovaerts.mollie.data.capture.CaptureListResponse;
import be.woutschoovaerts.mollie.data.capture.CaptureResponse;
import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.exception.MollieException;
import be.woutschoovaerts.mollie.handler.AbstractHandler;
import be.woutschoovaerts.mollie.util.Config;
import be.woutschoovaerts.mollie.util.ObjectMapperService;
import be.woutschoovaerts.mollie.util.QueryParams;
import com.fasterxml.jackson.core.type.TypeReference;
import kong.unirest.HttpResponse;
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

    public CaptureHandler(String baseUrl, Config config) {
        super(baseUrl, log, config);
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

            HttpResponse<String> response = get(uri, params, true);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<CaptureResponse>() {
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

            HttpResponse<String> response = get(uri, params, true);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<Pagination<CaptureListResponse>>() {
                    });

        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }
}
