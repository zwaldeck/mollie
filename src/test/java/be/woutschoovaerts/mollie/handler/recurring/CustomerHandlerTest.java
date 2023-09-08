package be.woutschoovaerts.mollie.handler.recurring;

import be.woutschoovaerts.mollie.data.customer.CustomerRequest;
import be.woutschoovaerts.mollie.data.payment.PaymentRequest;
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
class CustomerHandlerTest {

    @Mock
    private RestService restService;

    @InjectMocks
    private CustomerHandler handler;

    @Test
    void createCustomer() throws Exception {
        String uri = "/customers";

        CustomerRequest request = CustomerRequest.builder()
                .name(Optional.of("Wout"))
                .build();

        handler.createCustomer(request);

        verify(restService).post(eq(uri), eq(request), any(QueryParams.class), any(TypeReference.class));
    }

    @Test
    void getCustomer() throws Exception {
        String uri = "/customers/customer_id";

        handler.getCustomer("customer_id");

        verify(restService).get(eq(uri), any(QueryParams.class), eq(true), any(TypeReference.class));
    }

    @Test
    void updateCustomer() throws Exception {
        String uri = "/customers/customer_id";

        CustomerRequest request = CustomerRequest.builder()
                .name(Optional.of("Wout"))
                .build();

        handler.updateCustomer("customer_id", request);

        verify(restService).patch(eq(uri), eq(request), any(QueryParams.class), any(TypeReference.class));
    }

    @Test
    void deleteCustomer() throws Exception {
        String uri = "/customers/customer_id";

        handler.deleteCustomer("customer_id");

        verify(restService).delete(eq(uri), any(QueryParams.class), eq(true), any(TypeReference.class));
    }

    @Test
    void listCustomers() throws Exception {
        String uri = "/customers";

        handler.listCustomers();

        verify(restService).get(eq(uri), any(QueryParams.class), eq(true), any(TypeReference.class));
    }

    @Test
    void createCustomerPayment() throws Exception {
        String uri = "/customers/customer_id/payments";

        PaymentRequest request = PaymentRequest.builder()
                .description("payment description")
                .build();

        handler.createCustomerPayment("customer_id", request);

        verify(restService).post(eq(uri), eq(request), any(QueryParams.class), any(TypeReference.class));
    }

    @Test
    void listCustomerPayments() throws Exception {
        String uri = "/customers/customer_id/payments";

        handler.listCustomerPayments("customer_id");

        verify(restService).get(eq(uri), any(QueryParams.class), eq(true), any(TypeReference.class));
    }

}