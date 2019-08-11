package be.feelio.mollie.handler;

import be.feelio.mollie.data.order.OrderPaymentRequest;
import be.feelio.mollie.data.request.*;
import be.feelio.mollie.data.response.*;
import be.feelio.mollie.exception.MollieException;
import be.feelio.mollie.util.ObjectMapperService;
import be.feelio.mollie.util.QueryParams;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class OrderHandler extends AbstractHandler {

    private static final Logger log = LoggerFactory.getLogger(OrderHandler.class);

    public OrderHandler(String baseUrl) {
        super(baseUrl, log);
    }

    public OrderResponse createOrder(OrderRequest body) throws MollieException {
        return createOrder(body, QueryParams.EMPTY);
    }

    public OrderResponse createOrder(OrderRequest body, QueryParams params) throws MollieException {
        try {
            String uri = "/orders";

            HttpResponse<String> response = post(uri, body, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), OrderResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    public OrderResponse getOrder(String orderId) throws MollieException {
        return getOrder(orderId, QueryParams.EMPTY);
    }

    public OrderResponse getOrder(String orderId, QueryParams params) throws MollieException {
        try {
            String uri = "/orders/" + orderId;

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), OrderResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    public OrderListResponse getOrders() throws MollieException {
        return getOrders(QueryParams.EMPTY);
    }

    public OrderListResponse getOrders(QueryParams params) throws MollieException {
        try {
            String uri = "/orders";

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), OrderListResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    public OrderResponse updateOrder(String orderId, OrderUpdateRequest body) throws MollieException {
        return updateOrder(orderId, body, QueryParams.EMPTY);
    }

    public OrderResponse updateOrder(String orderId, OrderUpdateRequest body, QueryParams params)
            throws MollieException {
        try {
            String uri = "/orders/" + orderId;

            HttpResponse<String> response = patch(uri, body, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), OrderResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    public OrderResponse updateOrderLine(String orderId, String lineId, OrderLineUpdateRequest body)
            throws MollieException {
        return updateOrderLine(orderId, lineId, body, QueryParams.EMPTY);
    }

    public OrderResponse updateOrderLine(String orderId, String lineId, OrderLineUpdateRequest body, QueryParams params)
            throws MollieException {
        try {
            String uri = "/orders/" + orderId + "/lines/" + lineId;

            HttpResponse<String> response = patch(uri, body, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), OrderResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    public OrderResponse cancelOrder(String orderId) throws MollieException {
        return cancelOrder(orderId, QueryParams.EMPTY);
    }

    public OrderResponse cancelOrder(String orderId, QueryParams params) throws MollieException {
        try {
            String uri = "/orders/" + orderId;

            HttpResponse<String> response = delete(uri, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), OrderResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    public void cancelOrderLines(String orderId, CancelOrderLinesRequest body) throws MollieException {
        cancelOrderLines(orderId, body, QueryParams.EMPTY);
    }

    public void cancelOrderLines(String orderId, CancelOrderLinesRequest body, QueryParams params)
            throws MollieException {
        try {
            String uri = "/orders/" + orderId + "/lines";

            delete(uri, body, params);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    public PaymentResponse createOrderPayment(String orderId, OrderPaymentRequest body) throws MollieException {
        return createOrderPayment(orderId, body, QueryParams.EMPTY);
    }

    public PaymentResponse createOrderPayment(String orderId, OrderPaymentRequest body, QueryParams params)
            throws MollieException {
        try {
            String uri = "/orders/" + orderId + "/payments";

            HttpResponse<String> response = post(uri, body, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), PaymentResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    public RefundResponse createOrderRefund(String orderId, OrderRefundRequest body) throws MollieException {
        return createOrderRefund(orderId, body, QueryParams.EMPTY);
    }

    public RefundResponse createOrderRefund(String orderId, OrderRefundRequest body, QueryParams params)
            throws MollieException {
        try {
            String uri = "/orders/" + orderId + "/refunds";

            HttpResponse<String> response = post(uri, body, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), RefundResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    public OrderRefundListResponse getOrderRefunds(String orderId) throws MollieException {
        return getOrderRefunds(orderId, QueryParams.EMPTY);
    }

    public OrderRefundListResponse getOrderRefunds(String orderId, QueryParams params) throws MollieException {
        try {
            String uri = "/orders/" + orderId + "/refunds";

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper()
                    .readValue(response.getBody(), OrderRefundListResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }
}
