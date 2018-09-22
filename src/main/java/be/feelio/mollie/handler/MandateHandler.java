package be.feelio.mollie.handler;

import be.feelio.mollie.exception.MollieException;
import be.feelio.mollie.json.common.Pagination;
import be.feelio.mollie.json.request.MandateRequest;
import be.feelio.mollie.json.response.MandateListResponse;
import be.feelio.mollie.json.response.MandateResponse;
import be.feelio.mollie.util.ObjectMapperService;
import be.feelio.mollie.util.QueryParams;
import com.fasterxml.jackson.core.type.TypeReference;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;

public class MandateHandler extends AbstractHandler {

    public MandateHandler(String baseUrl) {
        super(baseUrl);
    }

    public MandateResponse createMandate(String customerId, MandateRequest body) throws MollieException {
        return createMandate(customerId, body, QueryParams.EMPTY);
    }

    public MandateResponse createMandate(String customerId, MandateRequest body, QueryParams params)
            throws MollieException {
        try {
            HttpResponse<String> response = Unirest.post(baseUrl + "/customers/" + customerId +
                    "/mandates" + params.toString())
                    .body(body)
                    .asString();

            validateResponse(response);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), MandateResponse.class);
        } catch (UnirestException | IOException ex) {
            throw new MollieException(ex);
        }
    }

    public MandateResponse getMandate(String customerId, String mandateId) throws MollieException {
        return getMandate(customerId, mandateId, QueryParams.EMPTY);
    }

    public MandateResponse getMandate(String customerId, String mandateId, QueryParams params)
            throws MollieException {
        try {
            HttpResponse<String> response = Unirest.get(baseUrl + "/customers/" + customerId +
                    "/mandates/" + mandateId + params.toString())
                    .asString();

            validateResponse(response);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), MandateResponse.class);
        } catch (UnirestException | IOException ex) {
            throw new MollieException(ex);
        }
    }

    public void revokeMandate(String customerId, String mandateId) throws MollieException {
        revokeMandate(customerId, mandateId, QueryParams.EMPTY);
    }

    public void revokeMandate(String customerId, String mandateId, QueryParams params)
            throws MollieException {
        try {
            HttpResponse<String> response = Unirest.delete(baseUrl + "/customers/" + customerId +
                    "/mandates/" + mandateId + params.toString())
                    .asString();

            validateResponse(response);
        } catch (UnirestException | IOException ex) {
            throw new MollieException(ex);
        }
    }

    public Pagination<MandateListResponse> listMandates(String customerId) throws MollieException {
        return listMandates(customerId, QueryParams.EMPTY);
    }


    public Pagination<MandateListResponse> listMandates(String customerId, QueryParams params) throws MollieException {
        try {
            HttpResponse<String> response = Unirest.get(baseUrl + "/customers/" + customerId +
                    "/mandates" + params.toString())
                    .asString();

            validateResponse(response);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<Pagination<MandateListResponse>>() {
                    });
        } catch (UnirestException | IOException ex) {
            throw new MollieException(ex);
        }
    }
}
