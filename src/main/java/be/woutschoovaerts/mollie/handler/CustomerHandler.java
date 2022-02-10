package be.woutschoovaerts.mollie.handler;

import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.data.customer.CustomerListResponse;
import be.woutschoovaerts.mollie.data.customer.CustomerRequest;
import be.woutschoovaerts.mollie.data.customer.CustomerResponse;
import be.woutschoovaerts.mollie.data.payment.PaymentListResponse;
import be.woutschoovaerts.mollie.data.payment.PaymentRequest;
import be.woutschoovaerts.mollie.data.payment.PaymentResponse;
import be.woutschoovaerts.mollie.exception.MollieException;
import be.woutschoovaerts.mollie.util.Config;
import be.woutschoovaerts.mollie.util.QueryParams;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles the Customer API <a href="https://docs.mollie.com/reference/v2/customers-api/create-customer">Mollie docs</a>
 *
 * @author Wout Schoovaerts
 */
public class CustomerHandler extends AbstractHandler {

    private static final Logger log = LoggerFactory.getLogger(CustomerHandler.class);

    public CustomerHandler(String baseUrl, Config config) {
        super(baseUrl, log, config);
    }

    /**
     * Creates a simple minimal representation of a customer in the Mollie API to use for the Mollie Checkout and Recurring features.
     *
     * @param body CustomerRequest object
     * @return CustomerResponse object
     * @throws MollieException when something went wrong
     */
    public CustomerResponse createCustomer(CustomerRequest body) throws MollieException {
        return createCustomer(body, QueryParams.EMPTY);
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
        String uri = "/customers";

        return post(uri, body, params, new TypeReference<>() {
        });
    }

    /**
     * Retrieve a single customer by its ID.
     *
     * @param customerId a customer id
     * @return CustomerResponse object
     * @throws MollieException when something went wrong
     */
    public CustomerResponse getCustomer(String customerId) throws MollieException {
        return getCustomer(customerId, QueryParams.EMPTY);
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
        String uri = "/customers/" + customerId;

        return get(uri, params, new TypeReference<>() {
        });
    }

    /**
     * Update an existing customer.
     *
     * @param body CustomerRequest object
     * @return CustomerResponse object
     * @throws MollieException when something went wrong
     */
    public CustomerResponse updateCustomer(String customerId, CustomerRequest body) throws MollieException {
        return updateCustomer(customerId, body, QueryParams.EMPTY);
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
        String uri = "/customers/" + customerId;

        return patch(uri, body, params, new TypeReference<>() {
        });
    }

    /**
     * Delete a customer. All mandates and subscriptions created for this customer will be canceled as well.
     *
     * @param customerId a customer id
     * @throws MollieException when something went wrong
     */
    public void deleteCustomer(String customerId) throws MollieException {
        deleteCustomer(customerId, QueryParams.EMPTY);
    }

    /**
     * Delete a customer. All mandates and subscriptions created for this customer will be canceled as well.
     *
     * @param customerId a customer id
     * @param params     A map of query parameters
     * @throws MollieException when something went wrong
     */
    public void deleteCustomer(String customerId, QueryParams params) throws MollieException {
        String uri = "/customers/" + customerId;

        delete(uri, params, new TypeReference<>() {
        });
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
        return listCustomers(QueryParams.EMPTY);
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
        String uri = "/customers";

        return get(uri, params, new TypeReference<>() {
        });
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
        return createCustomerPayment(customerId, body, QueryParams.EMPTY);
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
        String uri = "/customers/" + customerId + "/payments";

        return post(uri, body, params, new TypeReference<>() {
        });
    }

    /**
     * Retrieve all payments linked to the customer.
     *
     * @param customerId a customer id
     * @return paginated list of PaymentResponse objects
     * @throws MollieException when something went wrong
     */
    public Pagination<PaymentListResponse> listCustomerPayments(String customerId) throws MollieException {
        return listCustomerPayments(customerId, QueryParams.EMPTY);
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
        String uri = "/customers/" + customerId + "/payments";

        return get(uri, params, new TypeReference<>() {
        });
    }
}
