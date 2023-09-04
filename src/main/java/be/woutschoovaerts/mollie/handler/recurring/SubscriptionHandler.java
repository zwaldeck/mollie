package be.woutschoovaerts.mollie.handler.recurring;

import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.data.payment.PaymentListResponse;
import be.woutschoovaerts.mollie.data.subscription.SubscriptionListResponse;
import be.woutschoovaerts.mollie.data.subscription.SubscriptionRequest;
import be.woutschoovaerts.mollie.data.subscription.SubscriptionResponse;
import be.woutschoovaerts.mollie.data.subscription.UpdateSubscriptionRequest;
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
 * Handles the Subscriptions API <a href="https://docs.mollie.com/reference/v2/subscriptions-api/create-subscription">Mollie docs</a>
 *
 * @author Wout Schoovaerts
 */
public class SubscriptionHandler extends AbstractHandler {

    private static final Logger log = LoggerFactory.getLogger(SubscriptionHandler.class);

    public SubscriptionHandler(String baseUrl, Config config) {
        super(baseUrl, log, config);
    }

    /**
     * With subscriptions, you can schedule recurring payments to take place at regular intervals.
     *
     * @param customerId a customer id
     * @param body       SubscriptionRequest object
     * @return SubscriptionResponse object
     * @throws MollieException when something went wrong
     */
    public SubscriptionResponse createSubscription(String customerId, SubscriptionRequest body) throws MollieException {
        return createSubscription(customerId, body, new QueryParams());
    }

    /**
     * With subscriptions, you can schedule recurring payments to take place at regular intervals.
     *
     * @param customerId a customer id
     * @param body       SubscriptionRequest object
     * @param params     A map of query parameters
     * @return SubscriptionResponse object
     * @throws MollieException when something went wrong
     */
    public SubscriptionResponse createSubscription(String customerId, SubscriptionRequest body, QueryParams params)
            throws MollieException {
        try {
            String uri = "/customers/" + customerId + "/subscriptions";

            HttpResponse<String> response = post(uri, body, params);

            return ObjectMapperService.getInstance().getMapper()
                    .readValue(response.getBody(), SubscriptionResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Retrieve a subscription by its ID and its customer’s ID.
     *
     * @param customerId     a customer id
     * @param subscriptionId a subscription id
     * @return SubscriptionResponse object
     * @throws MollieException when something went wrong
     */
    public SubscriptionResponse getSubscription(String customerId, String subscriptionId) throws MollieException {
        return getSubscription(customerId, subscriptionId, new QueryParams());
    }

    /**
     * Retrieve a subscription by its ID and its customer’s ID.
     *
     * @param customerId     a customer id
     * @param subscriptionId a subscription id
     * @param params         A map of query parameters
     * @return SubscriptionResponse object
     * @throws MollieException when something went wrong
     */
    public SubscriptionResponse getSubscription(String customerId, String subscriptionId, QueryParams params)
            throws MollieException {
        try {
            String uri = "/customers/" + customerId + "/subscriptions/" + subscriptionId;

            HttpResponse<String> response = get(uri, params, true);

            return ObjectMapperService.getInstance().getMapper()
                    .readValue(response.getBody(), SubscriptionResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Cancel a subscription
     *
     * @param customerId     a customer id
     * @param subscriptionId a subscription id
     * @return SubscriptionResponse object
     * @throws MollieException when something went wrong
     */
    public SubscriptionResponse cancelSubscription(String customerId, String subscriptionId) throws MollieException {
        return cancelSubscription(customerId, subscriptionId, new QueryParams());
    }

    /**
     * Cancel a subscription
     *
     * @param customerId     a customer id
     * @param subscriptionId a subscription id
     * @param params         A map of query parameters
     * @return SubscriptionResponse object
     * @throws MollieException when something went wrong
     */
    public SubscriptionResponse cancelSubscription(String customerId, String subscriptionId, QueryParams params)
            throws MollieException {
        try {
            String uri = "/customers/" + customerId + "/subscriptions/" + subscriptionId;

            HttpResponse<String> response = delete(uri, params, true);

            return ObjectMapperService.getInstance().getMapper()
                    .readValue(response.getBody(), SubscriptionResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Retrieve all subscriptions of a customer.
     *
     * @param customerId a customer id
     * @return paginated list of Subscription objects
     * @throws MollieException when something went wrong
     */
    public Pagination<SubscriptionListResponse> listSubscriptions(String customerId) throws MollieException {
        return listSubscriptions(customerId, new QueryParams());
    }

    /**
     * Retrieve all subscriptions
     *
     * @return paginated list of Subscription objects
     * @throws MollieException when something went wrong
     */
    public Pagination<SubscriptionListResponse> listAllSubscriptions() throws MollieException {
        return listAllSubscriptions(new QueryParams());
    }

    /**
     * Retrieve all subscriptions
     *
     * @param params A map of query params
     * @return paginated list of Subscription objects
     * @throws MollieException when something went wrong
     */
    public Pagination<SubscriptionListResponse> listAllSubscriptions(QueryParams params) throws MollieException {
        try {
            String uri = "/subscriptions";

            HttpResponse<String> response = get(uri, params,true);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<Pagination<SubscriptionListResponse>>() {
                    });
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }


    /**
     * Retrieve all subscriptions of a customer.
     *
     * @param customerId a customer id
     * @param params     A map of query parameters
     * @return paginated list of Subscription objects
     * @throws MollieException when something went wrong
     */
    public Pagination<SubscriptionListResponse> listSubscriptions(String customerId, QueryParams params)
            throws MollieException {
        try {
            String uri = "/customers/" + customerId + "/subscriptions";

            HttpResponse<String> response = get(uri, params, true);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<Pagination<SubscriptionListResponse>>() {
                    });
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Retrieve all payments of a specific subscriptions of a customer.
     *
     * @param customerId     a customer id
     * @param subscriptionId a subscription id
     * @return paginated list of PaymentResponse objects
     * @throws MollieException when something went wrong
     */
    public Pagination<PaymentListResponse> listSubscriptionPayments(String customerId, String subscriptionId)
            throws MollieException {
        return listSubscriptionPayments(customerId, subscriptionId, new QueryParams());
    }

    /**
     * Retrieve all payments of a specific subscriptions of a customer.
     *
     * @param customerId     a customer id
     * @param subscriptionId a subscription id
     * @param params         A map of query parameters
     * @return paginated list of PaymentResponse objects
     * @throws MollieException when something went wrong
     */
    public Pagination<PaymentListResponse> listSubscriptionPayments(String customerId, String subscriptionId,
                                                                    QueryParams params) throws MollieException {
        try {
            String uri = "/customers/" + customerId + "/subscriptions/" + subscriptionId + "/payments";

            HttpResponse<String> response = get(uri, params, true);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<Pagination<PaymentListResponse>>() {
                    });
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Update a subscription.
     * <p>
     * You cannot update a canceled subscription.
     *
     * @param customerId     a customer id
     * @param subscriptionId a subscription id
     * @param body           UpdateSubscriptionRequest object
     * @return SubscriptionResponse object
     * @throws MollieException when something went wrong
     */
    public SubscriptionResponse updateSubscription(String customerId, String subscriptionId,
                                                   UpdateSubscriptionRequest body) throws MollieException {
        return updateSubscription(customerId, subscriptionId, body, new QueryParams());
    }

    /**
     * Update a subscription.
     * <p>
     * You cannot update a canceled subscription.
     *
     * @param customerId     a customer id
     * @param subscriptionId a subscription id
     * @param body           UpdateSubscriptionRequest object
     * @param params         A map of query parameters
     * @return SubscriptionResponse object
     * @throws MollieException when something went wrong
     */
    public SubscriptionResponse updateSubscription(String customerId, String subscriptionId,
                                                   UpdateSubscriptionRequest body, QueryParams params)
            throws MollieException {
        try {
            String uri = "/customers/" + customerId + "/subscriptions/" + subscriptionId;

            HttpResponse<String> response = patch(uri, body, params);

            return ObjectMapperService.getInstance().getMapper()
                    .readValue(response.getBody(), SubscriptionResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }
}
