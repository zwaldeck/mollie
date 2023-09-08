package be.woutschoovaerts.mollie.handler.recurring;

import be.woutschoovaerts.mollie.data.subscription.SubscriptionRequest;
import be.woutschoovaerts.mollie.data.subscription.UpdateSubscriptionRequest;
import be.woutschoovaerts.mollie.util.QueryParams;
import be.woutschoovaerts.mollie.util.RestService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SubscriptionHandlerTest {

    @Mock
    private RestService restService;

    @InjectMocks
    private SubscriptionHandler handler;

    @Test
    void createSubscription() throws Exception {
        String uri = "/customers/customer_id/subscriptions";

        SubscriptionRequest request = SubscriptionRequest.builder()
                .description("subscription description")
                .build();

        handler.createSubscription("customer_id", request);

        verify(restService).post(eq(uri), eq(request), any(QueryParams.class), any(TypeReference.class));
    }

    @Test
    void getSubscription() throws Exception {
        String uri = "/customers/customer_id/subscriptions/subscription_id";

        handler.getSubscription("customer_id", "subscription_id");

        verify(restService).get(eq(uri), any(QueryParams.class), eq(true), any(TypeReference.class));
    }

    @Test
    void cancelSubscription() throws Exception {
        String uri = "/customers/customer_id/subscriptions/subscription_id";

        handler.cancelSubscription("customer_id", "subscription_id");

        verify(restService).delete(eq(uri), any(QueryParams.class), eq(true), any(TypeReference.class));
    }

    @Test
    void listAllSubscriptions() throws Exception {
        String uri = "/subscriptions";

        handler.listAllSubscriptions();

        verify(restService).get(eq(uri), any(QueryParams.class), eq(true), any(TypeReference.class));
    }

    @Test
    void listSubscriptions() throws Exception {
        String uri = "/customers/customer_id/subscriptions";

        handler.listSubscriptions("customer_id");

        verify(restService).get(eq(uri), any(QueryParams.class), eq(true), any(TypeReference.class));
    }

    @Test
    void listSubscriptionPayments() throws Exception {
        String uri = "/customers/customer_id/subscriptions/subscription_id/payments";

        handler.listSubscriptionPayments("customer_id", "subscription_id");

        verify(restService).get(eq(uri), any(QueryParams.class), eq(true), any(TypeReference.class));
    }

    @Test
    void updateSubscription() throws Exception {
        String uri = "/customers/customer_id/subscriptions/subscription_id";

        UpdateSubscriptionRequest request = UpdateSubscriptionRequest.builder()
                .description(Optional.of("subscription description"))
                .build();

        handler.updateSubscription("customer_id", "subscription_id", request);

        verify(restService).patch(eq(uri), eq(request), any(QueryParams.class), any(TypeReference.class));
    }
}