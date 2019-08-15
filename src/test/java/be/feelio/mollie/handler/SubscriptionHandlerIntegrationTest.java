package be.feelio.mollie.handler;

import be.feelio.mollie.Client;
import be.feelio.mollie.ClientBuilder;
import be.feelio.mollie.data.common.Locale;
import be.feelio.mollie.exception.MollieException;
import be.feelio.mollie.data.common.Amount;
import be.feelio.mollie.data.common.Pagination;
import be.feelio.mollie.data.customer.CustomerRequest;
import be.feelio.mollie.data.mandate.MandateRequest;
import be.feelio.mollie.data.subscription.SubscriptionRequest;
import be.feelio.mollie.data.subscription.UpdateSubscriptionRequest;
import be.feelio.mollie.data.customer.CustomerResponse;
import be.feelio.mollie.data.payment.PaymentListResponse;
import be.feelio.mollie.data.subscription.SubscriptionListResponse;
import be.feelio.mollie.data.subscription.SubscriptionResponse;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Optional;
import java.util.OptionalInt;

import static be.feelio.mollie.IntegrationTestConstants.API_KEY;
import static org.junit.jupiter.api.Assertions.*;

class SubscriptionHandlerIntegrationTest {

    private Client client;

    @BeforeEach
    void setUp() {
        client = new ClientBuilder().withApiKey(API_KEY).build();
    }

    @Test
    void createSubscription() throws MollieException {
        CustomerResponse customer = createCustomer();

        assertNotNull(customer);

        SubscriptionResponse subscription = create(customer);

        assertNotNull(subscription);
    }

    @Test
    void getSubscription() throws MollieException {
        CustomerResponse customer = createCustomer();

        assertNotNull(customer);

        SubscriptionResponse subscription = create(customer);

        assertNotNull(subscription);

        subscription = client.subscriptions().getSubscription(customer.getId(), subscription.getId());

        assertNotNull(subscription);
    }

    @Test
    void cancelSubscription() throws MollieException {
        CustomerResponse customer = createCustomer();

        assertNotNull(customer);

        SubscriptionResponse subscription = create(customer);

        assertNotNull(subscription);

        subscription = client.subscriptions().cancelSubscription(customer.getId(), subscription.getId());

        assertNotNull(subscription);
    }

    @Test
    void listSubscriptions() throws MollieException {
        CustomerResponse customer = createCustomer();

        assertNotNull(customer);

        SubscriptionResponse subscription = create(customer);

        assertNotNull(subscription);

        Pagination<SubscriptionListResponse> subscriptions = client.subscriptions().listSubscriptions(customer.getId());

        assertNotNull(subscriptions);
    }

    @Test
    void listSubscriptionPayments() throws MollieException {
        CustomerResponse customer = createCustomer();

        assertNotNull(customer);

        SubscriptionResponse subscription = create(customer);

        assertNotNull(subscription);

        Pagination<PaymentListResponse> payments = client.subscriptions().listSubscriptionPayments(
                customer.getId(), subscription.getId());

        assertNotNull(payments);
    }

    @Test
    void updateSubscription() throws MollieException {
        CustomerResponse customer = createCustomer();

        assertNotNull(customer);

        SubscriptionResponse subscription = create(customer);

        assertNotNull(subscription);

        UpdateSubscriptionRequest request = UpdateSubscriptionRequest.builder()
                .description(Optional.of("New description"))
                .build();

        subscription = client.subscriptions().updateSubscription(customer.getId(), subscription.getId(), request);

        assertNotNull(subscription);
    }

    private CustomerResponse createCustomer() throws MollieException {
        long rand = RandomUtils.nextLong();
        CustomerRequest customerRequest = CustomerRequest.builder()
                .name(Optional.of("name" + rand))
                .email(Optional.of("name" + rand + "@feelio.be"))
                .locale(Optional.of(Locale.nl_BE))
                .build();

        CustomerResponse customer = client.customers().createCustomer(customerRequest);

        MandateRequest mandateRequest = MandateRequest.builder()
                .method("directdebit")
                .consumerName("John Doe")
                .consumerAccount("NL55INGB0000000000")
                .consumerBic(Optional.of("INGBNL2A"))
                .signatureDate(Optional.of(new Date()))
                .mandateReference(Optional.of("YOUR-COMPANY-MD13804"))
                .build();

        client.mandates().createMandate(customer.getId(), mandateRequest);

        return customer;
    }

    private SubscriptionResponse create(CustomerResponse customer) throws MollieException {
        SubscriptionRequest subscriptionRequest = SubscriptionRequest.builder()
                .amount(Amount.builder()
                        .currency("EUR")
                        .value("25.00")
                        .build())
                .times(Optional.of(4))
                .interval("3 months")
                .description("Quarterly payment")
                .webhookUrl(Optional.of("https://webshop.example.org/subscriptions/webhook/"))
                .build();

        return client.subscriptions().createSubscription(customer.getId(), subscriptionRequest);
    }
}