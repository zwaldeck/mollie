package be.woutschoovaerts.mollie.handler.recurring;

import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.data.customer.CustomerListResponse;
import be.woutschoovaerts.mollie.data.customer.CustomerRequest;
import be.woutschoovaerts.mollie.data.customer.CustomerResponse;
import be.woutschoovaerts.mollie.data.payment.PaymentListResponse;
import be.woutschoovaerts.mollie.data.payment.PaymentRequest;
import be.woutschoovaerts.mollie.data.payment.PaymentResponse;
import be.woutschoovaerts.mollie.exception.MollieException;
import be.woutschoovaerts.mollie.util.QueryParams;
import be.woutschoovaerts.mollie.util.RestService;
import com.fasterxml.jackson.core.type.TypeReference;
import kong.unirest.UnirestException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Handles the Customer API <a href="https://docs.mollie.com/reference/v2/customers-api/create-customer">Mollie docs</a>
 *
 * @author Wout Schoovaerts
 */
@RequiredArgsConstructor
public class CustomerHandler {

    private static final Logger log = LoggerFactory.getLogger(CustomerHandler.class);

    private static final TypeReference<CustomerResponse> CUSTOMER_RESPONSE_TYPE = new TypeReference<>() {
    };
    private static final TypeReference<Pagination<CustomerListResponse>> CUSTOMER_LIST_RESPONSE_TYPE = new TypeReference<>() {
    };
    private static final TypeReference<PaymentResponse> PAYMENT_RESPONSE_TYPE = new TypeReference<>() {
    };
    private static final TypeReference<Pagination<PaymentListResponse>> PAYMENT_LIST_RESPONSE_TYPE = new TypeReference<>() {
    };

    private final RestService restService;

    /**
     * Creates a simple minimal representation of a customer in the Mollie API to use for the Mollie Checkout and Recurring features.
     *
     * @param body CustomerRequest object
     * @return CustomerResponse object
     * @throws MollieException when something went wrong
     */
    public CustomerResponse createCustomer(CustomerRequest body) throws MollieException {
        return createCustomer(body, new QueryParams());
    }

    /**
     * Creates a simple minimal representation of a customer in the Mollie API to use for the Mollie Checkout and Recurring features.
     *
     * @param body   CustomerRequest object
     * @param params A map of query parameters
     * @return CustomerResponse object
     * @throws MollieException when something went wrong
     */
    public CustomerResponse createCustomer(CustomerRequest body, QueryParams params) throws MollieException {
        try {
            String uri = "/customers";

            return restService.post(uri, body, params, CUSTOMER_RESPONSE_TYPE);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Retrieve a single customer by its ID.
     *
     * @param customerId a customer id
     * @return CustomerResponse object
     * @throws MollieException when something went wrong
     */
    public CustomerResponse getCustomer(String customerId) throws MollieException {
        return getCustomer(customerId, new QueryParams());
    }

    /**
     * Retrieve a single customer by its ID.
     *
     * @param customerId a customer id
     * @param params     A map of query parameters
     * @return CustomerResponse object
     * @throws MollieException when something went wrong
     */
    public CustomerResponse getCustomer(String customerId, QueryParams params) throws MollieException {
        try {
            String uri = "/customers/" + customerId;

            return restService.get(uri, params, true, CUSTOMER_RESPONSE_TYPE);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Update an existing customer.
     *
     * @param body CustomerRequest object
     * @return CustomerResponse object
     * @throws MollieException when something went wrong
     */
    public CustomerResponse updateCustomer(String customerId, CustomerRequest body) throws MollieException {
        return updateCustomer(customerId, body, new QueryParams());
    }

    /**
     * Update an existing customer.
     *
     * @param body   CustomerRequest object
     * @param params A map of query parameters
     * @return CustomerResponse object
     * @throws MollieException when something went wrong
     */
    public CustomerResponse updateCustomer(String customerId, CustomerRequest body, QueryParams params)
            throws MollieException {
        try {
            String uri = "/customers/" + customerId;

            return restService.patch(uri, body, params, CUSTOMER_RESPONSE_TYPE);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Delete a customer. All mandates and subscriptions created for this customer will be canceled as well.
     *
     * @param customerId a customer id
     * @throws MollieException when something went wrong
     */
    public void deleteCustomer(String customerId) throws MollieException {
        deleteCustomer(customerId, new QueryParams());
    }

    /**
     * Delete a customer. All mandates and subscriptions created for this customer will be canceled as well.
     *
     * @param customerId a customer id
     * @param params     A map of query parameters
     * @throws MollieException when something went wrong
     */
    public void deleteCustomer(String customerId, QueryParams params) throws MollieException {
        try {
            String uri = "/customers/" + customerId;

            restService.delete(uri, params, true, new TypeReference<Void>() {});
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Retrieve all customers created.
     * <p>
     * The results are paginated.
     *
     * @return paginated list of CustomerResponse objects
     * @throws MollieException when something went wrong
     */
    public Pagination<CustomerListResponse> listCustomers() throws MollieException {
        return listCustomers(new QueryParams());
    }

    /**
     * Retrieve all customers created.
     * <p>
     * The results are paginated.
     *
     * @param params A map of query parameters
     * @return paginated list of CustomerResponse objects
     * @throws MollieException when something went wrong
     */
    public Pagination<CustomerListResponse> listCustomers(QueryParams params) throws MollieException {
        try {
            String uri = "/customers";

            return restService.get(uri, params, true, CUSTOMER_LIST_RESPONSE_TYPE);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Creates a payment for the customer.
     * <p>
     * Linking customers to payments enables a number of Mollie Checkout features
     *
     * @param customerId a customer id
     * @param body       PaymentRequest object
     * @return PaymentResponse object
     * @throws MollieException when something went wrong
     */
    public PaymentResponse createCustomerPayment(String customerId, PaymentRequest body) throws MollieException {
        return createCustomerPayment(customerId, body, new QueryParams());
    }

    /**
     * Creates a payment for the customer.
     * <p>
     * Linking customers to payments enables a number of Mollie Checkout features
     *
     * @param customerId a customer id
     * @param body       PaymentRequest object
     * @param params     A map of query parameters
     * @return PaymentResponse object
     * @throws MollieException when something went wrong
     */
    public PaymentResponse createCustomerPayment(String customerId, PaymentRequest body, QueryParams params)
            throws MollieException {
        try {
            String uri = "/customers/" + customerId + "/payments";

            return restService.post(uri, body, params, PAYMENT_RESPONSE_TYPE);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Retrieve all payments linked to the customer.
     *
     * @param customerId a customer id
     * @return paginated list of PaymentResponse objects
     * @throws MollieException when something went wrong
     */
    public Pagination<PaymentListResponse> listCustomerPayments(String customerId) throws MollieException {
        return listCustomerPayments(customerId, new QueryParams());
    }

    /**
     * Retrieve all payments linked to the customer.
     *
     * @param customerId a customer id
     * @param params     A map of query parameters
     * @return paginated list of PaymentResponse objects
     * @throws MollieException when something went wrong
     */
    public Pagination<PaymentListResponse> listCustomerPayments(String customerId, QueryParams params)
            throws MollieException {
        try {
            String uri = "/customers/" + customerId + "/payments";

            return restService.get(uri, params, true, PAYMENT_LIST_RESPONSE_TYPE);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }
}
