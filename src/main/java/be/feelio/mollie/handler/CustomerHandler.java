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

import java.io.IOException;

public class CustomerHandler extends AbstractHandler {

    public CustomerHandler(String baseUrl) {
        super(baseUrl);
    }

    public CustomerResponse createCustomer(CustomerRequest body) throws MollieException {
        return createCustomer(body, QueryParams.EMPTY);
    }

    public CustomerResponse createCustomer(CustomerRequest body, QueryParams params) throws MollieException {
        try {
            HttpResponse<String> response = Unirest.post(baseUrl + "/customers" + params.toString())
                    .body(body)
                    .asString();

            validateResponse(response);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), CustomerResponse.class);
        } catch (UnirestException | IOException ex) {
            throw new MollieException(ex);
        }
    }

    public CustomerResponse getCustomer(String customerId) throws MollieException {
        return getCustomer(customerId, QueryParams.EMPTY);
    }

    public CustomerResponse getCustomer(String customerId, QueryParams params) throws MollieException {
        try {
            HttpResponse<String> response = Unirest.get(baseUrl + "/customers/" + customerId + params.toString())
                    .asString();

            validateResponse(response);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), CustomerResponse.class);
        } catch (UnirestException | IOException ex) {
            throw new MollieException(ex);
        }
    }

    public CustomerResponse updateCustomer(String customerId, CustomerRequest body) throws MollieException {
        return updateCustomer(customerId, body, QueryParams.EMPTY);
    }

    public CustomerResponse updateCustomer(String customerId, CustomerRequest body, QueryParams params)
            throws MollieException {
        try {
            HttpResponse<String> response = Unirest.patch(baseUrl + "/customers/" + customerId + params.toString())
                    .body(body)
                    .asString();

            validateResponse(response);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), CustomerResponse.class);
        } catch (UnirestException | IOException ex) {
            throw new MollieException(ex);
        }
    }

    public void deleteCustomer(String customerId) throws MollieException {
        deleteCustomer(customerId, QueryParams.EMPTY);
    }

    public void deleteCustomer(String customerId, QueryParams params) throws MollieException {
        try {
            HttpResponse<String> response = Unirest.delete(baseUrl + "/customers/" + customerId + params.toString())
                    .asString();

            validateResponse(response);

        } catch (UnirestException | IOException ex) {
            throw new MollieException(ex);
        }
    }

    public Pagination<CustomerListResponse> getCustomers() throws MollieException {
        return getCustomers(QueryParams.EMPTY);
    }

    public Pagination<CustomerListResponse> getCustomers(QueryParams params) throws MollieException {
        try {
            HttpResponse<String> response = Unirest.get(baseUrl + "/customers" + params.toString())
                    .asString();

            validateResponse(response);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<Pagination<CustomerListResponse>>() {
                    });
        } catch (UnirestException | IOException ex) {
            throw new MollieException(ex);
        }
    }

    public PaymentResponse createCustomerPayment(String customerId, PaymentRequest body) throws MollieException {
        return createCustomerPayment(customerId, body, QueryParams.EMPTY);
    }

    public PaymentResponse createCustomerPayment(String customerId, PaymentRequest body, QueryParams params)
            throws MollieException {
        try {
            HttpResponse<String> response = Unirest.post(baseUrl + "/customers/" + customerId +
                    "/payments" + params.toString())
                    .body(body)
                    .asString();

            validateResponse(response);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), PaymentResponse.class);
        } catch (UnirestException | IOException ex) {
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
