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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ChargebackHandler extends AbstractHandler {

    private static final Logger log = LoggerFactory.getLogger(ChargebackHandler.class);

    public ChargebackHandler(String baseUrl) {
        super(baseUrl);
    }

    public ChargebackResponse getChargeback(String paymentId, String chargebackId) throws MollieException {
        return getChargeback(paymentId, chargebackId, QueryParams.EMPTY);
    }

    public ChargebackResponse getChargeback(String paymentId, String chargebackId, QueryParams params)
            throws MollieException {
        try {
            String url = baseUrl + "/payments/" + paymentId +
                    "/chargebacks/" + chargebackId + params.toString();

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

    public Pagination<ChargebackListResponse> listChargebacks() throws MollieException {
        try {
            String url = baseUrl + "/chargebacks";

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

    public Pagination<ChargebackListResponse> listChargebacks(String paymentId) throws MollieException {
        try {
            String url = baseUrl + "/payments/" + paymentId + "/chargebacks";

            log.info("Executing 'GET {}'", url);
            HttpResponse<String> response = Unirest.get(url)
                    .asString();


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
