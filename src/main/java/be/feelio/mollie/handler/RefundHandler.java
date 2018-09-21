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

import java.io.IOException;

public class RefundHandler extends AbstractHandler {

    public RefundHandler(String baseUrl) {
        super(baseUrl);
    }

    public RefundResponse createRefund(String paymentId, RefundRequest body) throws MollieException {
        return createRefund(paymentId, body, QueryParams.EMPTY);
    }

    public RefundResponse createRefund(String paymentId, RefundRequest body, QueryParams params)
            throws MollieException {
        try {
            HttpResponse<String> response = Unirest.post(baseUrl + "/payments/" + paymentId +
                    "/refunds" + params.toString())
                    .body(body)
                    .asString();

            validateResponse(response);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), RefundResponse.class);
        } catch (UnirestException | IOException ex) {
            throw new MollieException(ex);
        }
    }

    public RefundResponse getRefund(String paymentId, String refundId) throws MollieException {
        try {
            HttpResponse<String> response = Unirest.get(baseUrl + "/payments/" + paymentId + "/refunds/" + refundId)
                    .asString();

            validateResponse(response);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), RefundResponse.class);
        } catch (UnirestException | IOException ex) {
            throw new MollieException(ex);
        }
    }
    public void cancelRefund(String paymentId, String refundId) throws MollieException {
        cancelRefund(paymentId, refundId, QueryParams.EMPTY);
    }

    public void cancelRefund(String paymentId, String refundId, QueryParams params) throws MollieException {
        try {
            HttpResponse<String> response = Unirest.delete(baseUrl + "/payments/" + paymentId +
                    "/refunds/" + refundId + params.toString())
                    .asString();

            validateResponse(response);
        } catch (UnirestException | IOException ex) {
            throw new MollieException(ex);
        }
    }

    public Pagination<RefundListResponse> listRefunds() throws MollieException {
        return listRefunds(QueryParams.EMPTY);
    }

    public Pagination<RefundListResponse> listRefunds(QueryParams params) throws MollieException {
        try {
            HttpResponse<String> response = Unirest.get(baseUrl + "/refunds" + params.toString())
                    .asString();

            validateResponse(response);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<Pagination<RefundListResponse>>() {
                    });
        } catch (UnirestException | IOException ex) {
            throw new MollieException(ex);
        }
    }

    public Pagination<RefundListResponse> listRefunds(String paymentId) throws MollieException {
        return listRefunds(paymentId, QueryParams.EMPTY);
    }

    public Pagination<RefundListResponse> listRefunds(String paymentId, QueryParams params) throws MollieException {
        try {
            HttpResponse<String> response = Unirest.get(baseUrl + "/payments/" + paymentId +
                    "/refunds" + params.toString())
                    .asString();

            validateResponse(response);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<Pagination<RefundListResponse>>() {
                    });
        } catch (UnirestException | IOException ex) {
            throw new MollieException(ex);
        }
    }
}
