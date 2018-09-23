package be.feelio.mollie.handler;

import be.feelio.mollie.exception.MollieException;
import be.feelio.mollie.json.common.Pagination;
import be.feelio.mollie.json.request.SubscriptionRequest;
import be.feelio.mollie.json.request.UpdateSubscriptionRequest;
import be.feelio.mollie.json.response.PaymentListResponse;
import be.feelio.mollie.json.response.SubscriptionListResponse;
import be.feelio.mollie.json.response.SubscriptionResponse;
import be.feelio.mollie.util.ObjectMapperService;
import be.feelio.mollie.util.QueryParams;
import com.fasterxml.jackson.core.type.TypeReference;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;

public class SubscriptionHandler extends AbstractHandler {

    public SubscriptionHandler(String baseUrl) {
        super(baseUrl);
    }

    public SubscriptionResponse createSubscription(String customerId, SubscriptionRequest body) throws MollieException {
        return createSubscription(customerId, body, QueryParams.EMPTY);
    }

    public SubscriptionResponse createSubscription(String customerId, SubscriptionRequest body, QueryParams params)
            throws MollieException {
        try {
            HttpResponse<String> response = Unirest.post(baseUrl + "/customers/" + customerId +
                    "/subscriptions" + params.toString())
                    .body(body)
                    .asString();

            validateResponse(response);

            return ObjectMapperService.getInstance().getMapper()
                    .readValue(response.getBody(), SubscriptionResponse.class);
        } catch (UnirestException | IOException ex) {
            throw new MollieException(ex);
        }
    }

    public SubscriptionResponse getSubscription(String customerId, String subscriptionId) throws MollieException {
        return getSubscription(customerId, subscriptionId, QueryParams.EMPTY);
    }

    public SubscriptionResponse getSubscription(String customerId, String subscriptionId, QueryParams params)
            throws MollieException {
        try {
            HttpResponse<String> response = Unirest.get(baseUrl + "/customers/" + customerId +
                    "/subscriptions/" + subscriptionId + params.toString())
                    .asString();

            validateResponse(response);

            return ObjectMapperService.getInstance().getMapper()
                    .readValue(response.getBody(), SubscriptionResponse.class);
        } catch (UnirestException | IOException ex) {
            throw new MollieException(ex);
        }
    }

    public SubscriptionResponse cancelSubscription(String customerId, String subscriptionId) throws MollieException {
        return cancelSubscription(customerId, subscriptionId, QueryParams.EMPTY);
    }

    public SubscriptionResponse cancelSubscription(String customerId, String subscriptionId, QueryParams params)
            throws MollieException {
        try {
            HttpResponse<String> response = Unirest.delete(baseUrl + "/customers/" + customerId +
                    "/subscriptions/" + subscriptionId + params.toString())
                    .asString();

            validateResponse(response);

            return ObjectMapperService.getInstance().getMapper()
                    .readValue(response.getBody(), SubscriptionResponse.class);
        } catch (UnirestException | IOException ex) {
            throw new MollieException(ex);
        }
    }

    public Pagination<SubscriptionListResponse> listSubscriptions(String customerId) throws MollieException {
        return listSubscriptions(customerId, QueryParams.EMPTY);
    }


    public Pagination<SubscriptionListResponse> listSubscriptions(String customerId, QueryParams params)
            throws MollieException {
        try {
            HttpResponse<String> response = Unirest.get(baseUrl + "/customers/" + customerId +
                    "/subscriptions" + params.toString())
                    .asString();

            validateResponse(response);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<Pagination<SubscriptionListResponse>>() {
                    });
        } catch (UnirestException | IOException ex) {
            throw new MollieException(ex);
        }
    }

    public Pagination<PaymentListResponse> listSubscriptionPayments(String customerId, String subscriptionId)
            throws MollieException {
        return listSubscriptionPayments(customerId, subscriptionId, QueryParams.EMPTY);
    }


    public Pagination<PaymentListResponse> listSubscriptionPayments(String customerId, String subscriptionId,
                                                                    QueryParams params) throws MollieException {
        try {
            HttpResponse<String> response = Unirest.get(baseUrl + "/customers/" + customerId +
                    "/subscriptions/" + subscriptionId + "/payments" + params.toString())
                    .asString();

            validateResponse(response);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<Pagination<PaymentListResponse>>() {
                    });
        } catch (UnirestException | IOException ex) {
            throw new MollieException(ex);
        }
    }

    public SubscriptionResponse updateSubscription(String customerId, String subscriptionId,
                                                   UpdateSubscriptionRequest body) throws MollieException {
        return updateSubscription(customerId, subscriptionId, body, QueryParams.EMPTY);
    }

    public SubscriptionResponse updateSubscription(String customerId, String subscriptionId,
                                                   UpdateSubscriptionRequest body, QueryParams params)
            throws MollieException {
        try {
            HttpResponse<String> response = Unirest.patch(baseUrl + "/customers/" + customerId +
                    "/subscriptions/" + subscriptionId + params.toString())
                    .body(body)
                    .asString();

            validateResponse(response);

            return ObjectMapperService.getInstance().getMapper()
                    .readValue(response.getBody(), SubscriptionResponse.class);
        } catch (UnirestException | IOException ex) {
            throw new MollieException(ex);
        }
    }
}
