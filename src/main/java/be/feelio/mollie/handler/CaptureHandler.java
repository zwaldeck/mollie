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

import java.io.IOException;

public class CaptureHandler extends AbstractHandler {

    public CaptureHandler(String baseUrl) {
        super(baseUrl);
    }

    public CaptureResponse getCapture(String paymentId, String captureId) throws MollieException {
        return getCapture(paymentId, captureId, QueryParams.EMPTY);
    }

    public CaptureResponse getCapture(String paymentId, String captureId, QueryParams params)
            throws MollieException {
        try {
            HttpResponse<String> response = Unirest.get(baseUrl + "/payments/" + paymentId +
                    "/captures/" + captureId + params.toString()).asString();

            validateResponse(response);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<ChargebackResponse>() {
                    });
        } catch (UnirestException | IOException ex) {
            throw new MollieException(ex);
        }
    }

    public Pagination<CaptureListResponse> listCaptures(String paymentId) throws MollieException {
        return listCaptures(paymentId, QueryParams.EMPTY);
    }

    public Pagination<CaptureListResponse> listCaptures(String paymentId, QueryParams params) throws MollieException {
        try {
            HttpResponse<String> response = Unirest.get(baseUrl + "/payments/" + paymentId +
                    "/captures" + params.toString()).asString();

            validateResponse(response);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<Pagination<ChargebackListResponse>>() {
                    });
        } catch (UnirestException | IOException ex) {
            throw new MollieException(ex);
        }
    }
}
