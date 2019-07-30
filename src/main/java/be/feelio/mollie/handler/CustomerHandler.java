package be.feelio.mollie.handler;

import be.feelio.mollie.exception.MollieException;
import be.feelio.mollie.json.common.Pagination;
import be.feelio.mollie.json.request.CustomerRequest;
import be.feelio.mollie.json.request.PaymentRequest;
import be.feelio.mollie.json.response.CustomerListResponse;
import be.feelio.mollie.json.response.CustomerResponse;
import be.feelio.mollie.json.response.PaymentListResponse;
import be.feelio.mollie.json.response.PaymentResponse;
import be.feelio.mollie.util.ObjectMapperService;
import be.feelio.mollie.util.QueryParams;
import com.fasterxml.jackson.core.type.TypeReference;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Handles the Customer API <a href="https://docs.mollie.com/reference/v2/customers-api/create-customer">Mollie docs</a>
 *
 * @author Wout Schoovaerts
 */
public class CustomerHandler extends AbstractHandler {

    private static final Logger log = LoggerFactory.getLogger(CustomerHandler.class);

    public CustomerHandler(String baseUrl) {
        super(baseUrl, log);
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
        try {
            String uri = "/customers";

            HttpResponse<String> response = post(uri, body, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), CustomerResponse.class);
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
        try {
            String uri = "/customers/" + customerId;

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), CustomerResponse.class);
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
        try {
            String uri = "/customers/" + customerId;

            HttpResponse<String> response = patch(uri, body, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), CustomerResponse.class);
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
        try {
            String uri = "/customers/" + customerId;

            HttpResponse<String> response = delete(uri, params);

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
        try {
            String uri = "/customers";

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<Pagination<CustomerListResponse>>() {
                    });
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
        try {
            String uri = "/customers/" + customerId + "/payments";

            HttpResponse<String> response = post(uri, body, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), PaymentResponse.class);
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
        try {
            String uri = "/customers/" + customerId + "/payments";

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<Pagination<PaymentListResponse>>() {
                    });
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }
}
