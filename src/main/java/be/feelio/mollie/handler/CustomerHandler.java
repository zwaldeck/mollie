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
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CustomerHandler extends AbstractHandler {

    private static final Logger log = LoggerFactory.getLogger(CustomerHandler.class);

    public CustomerHandler(String baseUrl) {
        super(baseUrl);
    }

    public CustomerResponse createCustomer(CustomerRequest body) throws MollieException {
        return createCustomer(body, QueryParams.EMPTY);
    }

    public CustomerResponse createCustomer(CustomerRequest body, QueryParams params) throws MollieException {
        try {
            String url = baseUrl + "/customers" + params.toString();

            log.info("Executing 'POST {}'", url);
            HttpResponse<String> response = Unirest.post(url)
                    .body(body)
                    .asString();

            validateResponse(response);
            log.info("Successful response 'POST {}'", url);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), CustomerResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    public CustomerResponse getCustomer(String customerId) throws MollieException {
        return getCustomer(customerId, QueryParams.EMPTY);
    }

    public CustomerResponse getCustomer(String customerId, QueryParams params) throws MollieException {
        try {
            String url = baseUrl + "/customers/" + customerId + params.toString();

            log.info("Executing 'GET {}'", url);
            HttpResponse<String> response = Unirest.get(url)
                    .asString();

            validateResponse(response);
            log.info("Successful response 'GET {}'", url);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), CustomerResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    public CustomerResponse updateCustomer(String customerId, CustomerRequest body) throws MollieException {
        return updateCustomer(customerId, body, QueryParams.EMPTY);
    }

    public CustomerResponse updateCustomer(String customerId, CustomerRequest body, QueryParams params)
            throws MollieException {
        try {
            String url = baseUrl + "/customers/" + customerId + params.toString();

            log.info("Executing 'PATCH {}'", url);
            HttpResponse<String> response = Unirest.patch(url)
                    .body(body)
                    .asString();

            validateResponse(response);
            log.info("Successful response 'PATCH {}'", url);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), CustomerResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    public void deleteCustomer(String customerId) throws MollieException {
        deleteCustomer(customerId, QueryParams.EMPTY);
    }

    public void deleteCustomer(String customerId, QueryParams params) throws MollieException {
        try {
            String url = baseUrl + "/customers/" + customerId + params.toString();

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

    public Pagination<CustomerListResponse> getCustomers() throws MollieException {
        return getCustomers(QueryParams.EMPTY);
    }

    public Pagination<CustomerListResponse> getCustomers(QueryParams params) throws MollieException {
        try {
            String url = baseUrl + "/customers" + params.toString();

            log.info("Executing 'GET {}'", url);
            HttpResponse<String> response = Unirest.get(url)
                    .asString();

            validateResponse(response);
            log.info("Successful response 'GET {}'", url);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<Pagination<CustomerListResponse>>() {
                    });
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    public PaymentResponse createCustomerPayment(String customerId, PaymentRequest body) throws MollieException {
        return createCustomerPayment(customerId, body, QueryParams.EMPTY);
    }

    public PaymentResponse createCustomerPayment(String customerId, PaymentRequest body, QueryParams params)
            throws MollieException {
        try {
            String url = baseUrl + "/customers/" + customerId + "/payments" + params.toString();

            log.info("Executing 'POST {}'", url);
            HttpResponse<String> response = Unirest.post(url)
                    .body(body)
                    .asString();

            validateResponse(response);
            log.info("Successful response 'POST {}'", url);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), PaymentResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    public Pagination<PaymentListResponse> listCustomerPayments(String customerId) throws MollieException {
        return listCustomerPayments(customerId, QueryParams.EMPTY);
    }


    public Pagination<PaymentListResponse> listCustomerPayments(String customerId, QueryParams queryParams)
            throws MollieException {
        try {
            String url = baseUrl + "/customers/" + customerId + "/payments" + queryParams.toString();

            log.info("Executing 'GET {}'", url);
            HttpResponse<String> response = Unirest.get(url).asString();

            validateResponse(response);
            log.info("Successful response 'GET {}'", url);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<Pagination<PaymentListResponse>>() {
                    });
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }
}
