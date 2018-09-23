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
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CaptureHandler extends AbstractHandler {

    private static final Logger log = LoggerFactory.getLogger(CaptureHandler.class);

    public CaptureHandler(String baseUrl) {
        super(baseUrl);
    }

    public CaptureResponse getCapture(String paymentId, String captureId) throws MollieException {
        return getCapture(paymentId, captureId, QueryParams.EMPTY);
    }

    public CaptureResponse getCapture(String paymentId, String captureId, QueryParams params)
            throws MollieException {
        try {
            String url = baseUrl + "/payments/" + paymentId +
                    "/captures/" + captureId + params.toString();

            log.info("Executing 'GET {}'", url);
            HttpResponse<String> response = Unirest.get(url).asString();

            validateResponse(response);
            log.info("Successful response 'GET {}'", url);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<ChargebackResponse>() {
                    });
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    public Pagination<CaptureListResponse> listCaptures(String paymentId) throws MollieException {
        return listCaptures(paymentId, QueryParams.EMPTY);
    }

    public Pagination<CaptureListResponse> listCaptures(String paymentId, QueryParams params) throws MollieException {
        try {
            String url = baseUrl + "/payments/" + paymentId +
                    "/captures" + params.toString();

            log.info("Executing 'GET {}'", url);
            HttpResponse<String> response = Unirest.get(url).asString();

            validateResponse(response);
            log.info("Successful response 'GET {}'", url);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<Pagination<ChargebackListResponse>>() {
                    });
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }
}
