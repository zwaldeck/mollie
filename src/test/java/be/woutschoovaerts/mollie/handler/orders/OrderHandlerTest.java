package be.woutschoovaerts.mollie.handler.orders;

import be.woutschoovaerts.mollie.data.common.Amount;
import be.woutschoovaerts.mollie.data.order.*;
import be.woutschoovaerts.mollie.util.QueryParams;
import be.woutschoovaerts.mollie.util.RestService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderHandlerTest {

    @Mock
    private RestService restService;

    @InjectMocks
    private OrderHandler handler;

    @Test
    void createOrder() throws Exception {
        String uri = "/orders";

        OrderRequest request = OrderRequest.builder()
                .amount(Amount.builder().currency("EUR").value(new BigDecimal("10.00")).build())
                .orderNumber("ord-001")
                .lines(List.of())
                .build();

        handler.createOrder(request);

        verify(restService).post(eq(uri), eq(request), any(QueryParams.class), any(TypeReference.class));
    }

    @Test
    void getOrder() throws Exception {
        String uri = "/orders/order_id";

        handler.getOrder("order_id");

        verify(restService).get(eq(uri), any(QueryParams.class), eq(true), any(TypeReference.class));
    }

    @Test
    void getOrders() throws Exception {
        String uri = "/orders";

        handler.getOrders();

        verify(restService).get(eq(uri), any(QueryParams.class), eq(true), any(TypeReference.class));
    }

    @Test
    void updateOrder() throws Exception {
        String uri = "/orders/order_id";

        OrderUpdateRequest request = OrderUpdateRequest.builder()
                .orderNumber(Optional.of("updated_id"))
                .build();

        handler.updateOrder("order_id", request);

        verify(restService).patch(eq(uri), eq(request), any(QueryParams.class), any(TypeReference.class));
    }

    @Test
    void updateOrderLine() throws Exception {
        String uri = "/orders/order_id/lines/line_id";

        OrderLineUpdateRequest request = OrderLineUpdateRequest.builder()
                .name(Optional.of("updated_name"))
                .build();

        handler.updateOrderLine("order_id", "line_id", request);

        verify(restService).patch(eq(uri), eq(request), any(QueryParams.class), any(TypeReference.class));
    }

    @Test
    void cancelOrder() throws Exception {
        String uri = "/orders/order_id";

        handler.cancelOrder("order_id");

        verify(restService).delete(eq(uri), any(QueryParams.class), eq(true), any(TypeReference.class));
    }

    @Test
    void manageOrderLines() throws Exception {
        String uri = "/orders/order_id/lines";

        ManageOrderLineRequest request = ManageOrderLineRequest.builder()
                .operations(List.of(
                        OrderLineOperationRequest.cancelOperation(CancelOrderLineRequest.builder().build())
                ))
                .build();

        handler.manageOrderLines("order_id", request);

        verify(restService).patch(eq(uri), eq(request), any(QueryParams.class), any(TypeReference.class));
    }

    @Test
    void cancelOrderLines() throws Exception {
        String uri = "/orders/order_id/lines";

        CancelOrderLinesRequest request = CancelOrderLinesRequest.builder().build();

        handler.cancelOrderLines("order_id", request);

        verify(restService).delete(eq(uri), eq(request), any(QueryParams.class), any(TypeReference.class));
    }

    @Test
    void createOrderPayment() throws Exception {
        String uri = "/orders/order_id/payments";

        OrderPaymentRequest request = OrderPaymentRequest.builder().build();

        handler.createOrderPayment("order_id", request);

        verify(restService).post(eq(uri), eq(request), any(QueryParams.class), any(TypeReference.class));
    }

    @Test
    void createOrderRefund() throws Exception {
        String uri = "/orders/order_id/refunds";

        OrderRefundRequest request = OrderRefundRequest.builder().build();

        handler.createOrderRefund("order_id", request);

        verify(restService).post(eq(uri), eq(request), any(QueryParams.class), any(TypeReference.class));
    }

    @Test
    void getOrderRefunds() throws Exception {
        String uri = "/orders/order_id/refunds";

        handler.getOrderRefunds("order_id");

        verify(restService).get(eq(uri), any(QueryParams.class), any(TypeReference.class));
    }
}