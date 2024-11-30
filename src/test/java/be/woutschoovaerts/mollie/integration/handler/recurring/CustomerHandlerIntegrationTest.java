package be.woutschoovaerts.mollie.integration.handler.recurring;

import be.woutschoovaerts.mollie.Client;
import be.woutschoovaerts.mollie.ClientBuilder;
import be.woutschoovaerts.mollie.data.common.Amount;
import be.woutschoovaerts.mollie.data.common.Locale;
import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.data.customer.CustomerListResponse;
import be.woutschoovaerts.mollie.data.customer.CustomerRequest;
import be.woutschoovaerts.mollie.data.customer.CustomerResponse;
import be.woutschoovaerts.mollie.data.payment.PaymentListResponse;
import be.woutschoovaerts.mollie.data.payment.PaymentRequest;
import be.woutschoovaerts.mollie.data.payment.PaymentResponse;
import be.woutschoovaerts.mollie.exception.MollieException;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static be.woutschoovaerts.mollie.IntegrationTestConstants.API_KEY;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        Pagination<CustomerListResponse> response = client.customers().listCustomers();

        assertNotNull(response);
    }

    @Test
    void createCustomerPayment() throws MollieException {
        CustomerResponse customer = create();

        assertNotNull(customer);

        PaymentRequest request = PaymentRequest.builder()
                .amount(Amount.builder()
                        .currency("EUR")
                        .value(new BigDecimal("10.00"))
                        .build())
                .description("My first payment")
                .redirectUrl("https://webshop.example.org/order/12345/")
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
                .email(Optional.of("name" + rand + "@z-soft.be"))
                .locale(Optional.of(Locale.nl_BE))
                .build();

        return client.customers().createCustomer(customerRequest);
    }
}
