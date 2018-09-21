package be.feelio.mollie.handler;

import be.feelio.mollie.exception.MollieException;
import be.feelio.mollie.json.common.Pagination;
import be.feelio.mollie.json.response.ChargebackListResponse;
import be.feelio.mollie.json.response.ChargebackResponse;
import be.feelio.mollie.util.ObjectMapperService;
import be.feelio.mollie.util.QueryParams;
import com.fasterxml.jackson.core.type.TypeReference;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;

public class ChargebackHandler extends AbstractHandler {

    public ChargebackHandler(String baseUrl) {
        super(baseUrl);
    }


    public ChargebackResponse getChargeback(String paymentId, String chargebackId, QueryParams params)
            throws MollieException {
        try {
            HttpResponse<String> response = Unirest.get(baseUrl + "/payments/" + paymentId +
                    "/chargebacks/" + chargebackId + params.toString()).asString();

            validateResponse(response);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<ChargebackResponse>() {
                    });
        } catch (UnirestException | IOException ex) {
            throw new MollieException(ex);
        }
    }

    public Pagination<ChargebackListResponse> getChargebacks() throws MollieException {
        try {
            HttpResponse<String> response = Unirest.get(baseUrl + "/chargebacks").asString();

            validateResponse(response);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<Pagination<ChargebackListResponse>>() {
                    });
        } catch (UnirestException | IOException ex) {
            throw new MollieException(ex);
        }
    }

    public Pagination<ChargebackListResponse> getChargebacks(String paymentId) throws MollieException {
        try {
            HttpResponse<String> response = Unirest.get(baseUrl + "/payments/" + paymentId + "/chargebacks")
                    .asString();

            validateResponse(response);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<Pagination<ChargebackListResponse>>() {
                    });
        } catch (UnirestException | IOException ex) {
            throw new MollieException(ex);
        }
    }
}
