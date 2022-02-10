package be.woutschoovaerts.mollie.handler;

import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.data.payment.PaymentListResponse;
import be.woutschoovaerts.mollie.data.subscription.SubscriptionListResponse;
import be.woutschoovaerts.mollie.data.subscription.SubscriptionRequest;
import be.woutschoovaerts.mollie.data.subscription.SubscriptionResponse;
import be.woutschoovaerts.mollie.data.subscription.UpdateSubscriptionRequest;
import be.woutschoovaerts.mollie.exception.MollieException;
import be.woutschoovaerts.mollie.util.Config;
import be.woutschoovaerts.mollie.util.QueryParams;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        return createSubscription(customerId, body, QueryParams.EMPTY);
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
        String uri = "/customers/" + customerId + "/subscriptions";

        return post(uri, body, params, new TypeReference<>() {
        });
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
        return getSubscription(customerId, subscriptionId, QueryParams.EMPTY);
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
        String uri = "/customers/" + customerId + "/subscriptions/" + subscriptionId;

        return get(uri, params, new TypeReference<>() {
        });
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
        return cancelSubscription(customerId, subscriptionId, QueryParams.EMPTY);
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
        String uri = "/customers/" + customerId + "/subscriptions/" + subscriptionId;

        return delete(uri, params, new TypeReference<>() {
        });
    }

    /**
     * Retrieve all subscriptions of a customer.
     *
     * @param customerId a customer id
     * @return paginated list of Subscription objects
     * @throws MollieException when something went wrong
     */
    public Pagination<SubscriptionListResponse> listSubscriptions(String customerId) throws MollieException {
        return listSubscriptions(customerId, QueryParams.EMPTY);
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
        String uri = "/customers/" + customerId + "/subscriptions";

        return get(uri, params, new TypeReference<>() {
        });
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
        return listSubscriptionPayments(customerId, subscriptionId, QueryParams.EMPTY);
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
        String uri = "/customers/" + customerId + "/subscriptions/" + subscriptionId + "/payments";

        return get(uri, params, new TypeReference<>() {
        });
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
        return updateSubscription(customerId, subscriptionId, body, QueryParams.EMPTY);
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
        String uri = "/customers/" + customerId + "/subscriptions/" + subscriptionId;

        return patch(uri, body, params, new TypeReference<>() {
        });
    }
}
