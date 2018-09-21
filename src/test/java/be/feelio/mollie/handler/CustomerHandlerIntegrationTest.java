package be.feelio.mollie.handler;

import be.feelio.mollie.Client;
import be.feelio.mollie.ClientBuilder;
import be.feelio.mollie.exception.MollieException;
import be.feelio.mollie.json.common.Amount;
import be.feelio.mollie.json.common.Pagination;
import be.feelio.mollie.json.request.CustomerRequest;
import be.feelio.mollie.json.request.PaymentRequest;
import be.feelio.mollie.json.response.CustomerListResponse;
import be.feelio.mollie.json.response.CustomerResponse;
import be.feelio.mollie.json.response.PaymentListResponse;
import be.feelio.mollie.json.response.PaymentResponse;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static be.feelio.mollie.handler.IntegrationTestConstants.API_KEY;
import static org.junit.jupiter.api.Assertions.*;

class CustomerHandlerIntegrationTest {

    private Client client;

    @BeforeEach
    void setup() {
        client = new ClientBuilder().withApiKey(API_KEY).build();
    }

    @Test
    void createCustomer() throws MollieException {
        CustomerResponse response = create();

        assertNotNull(response);
    }

    @Test
    void getCustomer() throws MollieException {
        CustomerResponse customer = create();

        assertNotNull(customer);

        CustomerResponse response = client.customers().getCustomer(customer.getId());

        assertNotNull(response);

    }

    @Test
    void updateCustomer() throws MollieException {
        CustomerResponse customer = create();

        assertNotNull(customer);

        CustomerRequest customerRequest = CustomerRequest.builder()
                .name(Optional.of(customer.getName() + "_updated"))
                .build();

        CustomerResponse response = client.customers().updateCustomer(customer.getId(), customerRequest);

        assertNotNull(response);
    }

    @Test
    void deleteCustomer() throws MollieException {
        CustomerResponse customer = create();

        assertNotNull(customer);

        client.customers().deleteCustomer(customer.getId());
    }

    @Test
    void getCustomers() throws MollieException {
        Pagination<CustomerListResponse> response = client.customers().getCustomers();

        assertNotNull(response);
    }

    @Test
    void createCustomerPayment() throws MollieException {
        CustomerResponse customer = create();

        assertNotNull(customer);

        PaymentRequest request = PaymentRequest.builder()
                .amount(Amount.builder()
                        .currency("EUR")
                        .value("10.00")
                        .build())
                .description("My first payment")
                .redirectUrl(Optional.of("https://webshop.example.org/order/12345/"))
                .webhookUrl(Optional.of("https://webshop.example.org/payments/webhook/"))
                .build();

        PaymentResponse payment = client.customers().createCustomerPayment(customer.getId(), request);

        assertNotNull(payment);
    }

    @Test
    void listCustomers() throws MollieException {
        CustomerResponse customer = create();

        assertNotNull(customer);

        Pagination<PaymentListResponse> response = client.customers().listCustomerPayments(customer.getId());

        assertNotNull(response);

    }

    private CustomerResponse create() throws MollieException {
        long rand = RandomUtils.nextLong();
        CustomerRequest customerRequest = CustomerRequest.builder()
                .name(Optional.of("name" + rand))
                .email(Optional.of("name" + rand + "@feelio.be"))
                .locale(Optional.of("nl_BE"))
                .build();

        return client.customers().createCustomer(customerRequest);
    }
}