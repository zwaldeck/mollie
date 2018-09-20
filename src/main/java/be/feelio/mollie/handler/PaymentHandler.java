package be.feelio.mollie.handler;

import be.feelio.mollie.exception.MollieException;
import be.feelio.mollie.json.common.Pagination;
import be.feelio.mollie.json.request.PaymentRequest;
import be.feelio.mollie.json.response.PaymentListResponse;
import be.feelio.mollie.json.response.PaymentResponse;
import be.feelio.mollie.util.ObjectMapperService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;

// TODO support Mollie Connect/OAuth parameters
// TODO support QR codes
public class PaymentHandler extends AbstractHandler {


    public PaymentHandler(String baseUrl) {
        super(baseUrl);

    }

    public PaymentResponse createPayment(PaymentRequest body) throws MollieException {
        try {
            HttpResponse<String> response = Unirest.post(baseUrl + "/payments")
                    .body(body)
                    .asString();

            validateResponse(response);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), PaymentResponse.class);
        } catch (UnirestException | IOException ex) {
            throw new MollieException(ex);
        }
    }

    public PaymentResponse getPayment(String paymentId) throws MollieException {
        return getPayment(paymentId, false, false);
    }

    public PaymentResponse getPayment(String paymentId, boolean withRefunds, boolean withChargebacks)
            throws MollieException {
        try {
            String url = baseUrl + "/payments/" + paymentId;

            if (withRefunds && withChargebacks) {
                url += "?embed=refunds,chargebacks";
            } else if (withRefunds) {
                url += "?embed=refunds";
            } else if (withChargebacks) {
                url += "?embed=chargebacks";
            }

            HttpResponse<String> response = Unirest.get(url).asString();

            validateResponse(response);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), PaymentResponse.class);
        } catch (UnirestException | IOException ex) {
            throw new MollieException(ex);
        }
    }

    public PaymentResponse cancelPayment(String paymentId) throws MollieException {
        try {
            HttpResponse<String> response = Unirest.delete(baseUrl + "/payments/" + paymentId).asString();

            validateResponse(response);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), PaymentResponse.class);
        } catch (UnirestException | IOException ex) {
            throw new MollieException(ex);
        }
    }

    public Pagination<PaymentListResponse> listPayments() throws MollieException {
        return listPayments(null, -1);
    }

    public Pagination<PaymentListResponse> listPayments(String from) throws MollieException {
        return listPayments(from, -1);
    }

    public Pagination<PaymentListResponse> listPayments(int limit) throws MollieException {
        return listPayments(null, limit);
    }

    public Pagination<PaymentListResponse> listPayments(String from, int limit) throws MollieException {
        try {
            String url = baseUrl + "/payments";

            if (from != null && !from.isEmpty() && limit > 0) {
                url += "?limit=" + limit + "&from=" + from;
            } else if (from != null && !from.isEmpty()) {
                url += "?from=" + from;
            } else if (limit > 0) {
                url += "?limit=" + limit;
            }

            HttpResponse<String> response = Unirest.get(url).asString();

            validateResponse(response);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<Pagination<PaymentListResponse>>() {
                    });
        } catch (UnirestException | IOException ex) {
            throw new MollieException(ex);
        }
    }
}
