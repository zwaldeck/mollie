package be.feelio.mollie.handler;

import be.feelio.mollie.exception.MollieException;
import be.feelio.mollie.json.common.Pagination;
import be.feelio.mollie.json.request.RefundRequest;
import be.feelio.mollie.json.response.RefundListResponse;
import be.feelio.mollie.json.response.RefundResponse;
import be.feelio.mollie.util.ObjectMapperService;
import be.feelio.mollie.util.QueryParams;
import com.fasterxml.jackson.core.type.TypeReference;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class RefundHandler extends AbstractHandler {

    private static final Logger log = LoggerFactory.getLogger(RefundHandler.class);

    public RefundHandler(String baseUrl) {
        super(baseUrl);
    }

    public RefundResponse createRefund(String paymentId, RefundRequest body) throws MollieException {
        return createRefund(paymentId, body, QueryParams.EMPTY);
    }

    public RefundResponse createRefund(String paymentId, RefundRequest body, QueryParams params)
            throws MollieException {
        try {
            String url = baseUrl + "/payments/" + paymentId + "/refunds" + params.toString();

            log.info("Executing 'POST {}'", url);
            HttpResponse<String> response = Unirest.post(url)
                    .body(body)
                    .asString();

            validateResponse(response);
            log.info("Successful response 'POST {}'", url);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), RefundResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    public RefundResponse getRefund(String paymentId, String refundId) throws MollieException {
        try {
            String url = baseUrl + "/payments/" + paymentId + "/refunds/" + refundId;

            log.info("Executing 'GET {}'", url);
            HttpResponse<String> response = Unirest.get(url)
                    .asString();

            validateResponse(response);
            log.info("Successful response 'GET {}'", url);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), RefundResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }
    public void cancelRefund(String paymentId, String refundId) throws MollieException {
        cancelRefund(paymentId, refundId, QueryParams.EMPTY);
    }

    public void cancelRefund(String paymentId, String refundId, QueryParams params) throws MollieException {
        try {
            String url = baseUrl + "/payments/" + paymentId + "/refunds/" + refundId + params.toString();

            log.info("Executing 'DELETE {}'", url);
            HttpResponse<String> response = Unirest.delete(url)
                    .asString();

            validateResponse(response);
            log.info("Successful response 'DELETE {}'", url);

        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    public Pagination<RefundListResponse> listRefunds() throws MollieException {
        return listRefunds(QueryParams.EMPTY);
    }

    public Pagination<RefundListResponse> listRefunds(QueryParams params) throws MollieException {
        try {
            String url = baseUrl + "/refunds" + params.toString();

            log.info("Executing 'GET {}'", url);
            HttpResponse<String> response = Unirest.get(url)
                    .asString();

            validateResponse(response);
            log.info("Successful response 'GET {}'", url);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<Pagination<RefundListResponse>>() {
                    });
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    public Pagination<RefundListResponse> listRefunds(String paymentId) throws MollieException {
        return listRefunds(paymentId, QueryParams.EMPTY);
    }

    public Pagination<RefundListResponse> listRefunds(String paymentId, QueryParams params) throws MollieException {
        try {
            String url = baseUrl + "/payments/" + paymentId + "/refunds" + params.toString();

            log.info("Executing 'GET {}'", url);
            HttpResponse<String> response = Unirest.get(url)
                    .asString();

            validateResponse(response);
            log.info("Successful response 'GET {}'", url);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<Pagination<RefundListResponse>>() {
                    });
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }
}
