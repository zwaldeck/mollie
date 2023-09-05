package be.woutschoovaerts.mollie.handler.orders;

import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.data.order.*;
import be.woutschoovaerts.mollie.data.payment.PaymentResponse;
import be.woutschoovaerts.mollie.data.refund.RefundResponse;
import be.woutschoovaerts.mollie.exception.MollieException;
import be.woutschoovaerts.mollie.handler.AbstractHandler;
import be.woutschoovaerts.mollie.util.Config;
import be.woutschoovaerts.mollie.util.ObjectMapperService;
import be.woutschoovaerts.mollie.util.QueryParams;
import com.fasterxml.jackson.core.type.TypeReference;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Handles the Order API <a href="https://docs.mollie.com/reference/v2/orders-api/create-order">Mollie docs</a>
 *
 * @author Wout Schoovaerts
 */
public class OrderHandler extends AbstractHandler {

    private static final Logger log = LoggerFactory.getLogger(OrderHandler.class);

    public OrderHandler(String baseUrl, Config config) {
        super(baseUrl, log, config);
    }

    /**
     * Using the Orders API is the preferred approach when integrating the Mollie API into e-commerce applications such as webshops. If you want to use pay after delivery methods such as Klarna Pay later, using the Orders API is mandatory.
     * <p>
     * Creating an order will automatically create the required payment to allow your customer to pay for the order.
     * <p>
     * Once you have created an order, you should redirect your customer to the URL in the _links.checkout property from the response.
     * <p>
     * Note that when the payment fails, expires or is canceled, you can create a new payment using the Create order payment API. This is only possible for orders that have a created status.
     *
     * @param body OrderRequest object
     * @return OrderResponse object
     * @throws MollieException when something went wrong
     */
    public OrderResponse createOrder(OrderRequest body) throws MollieException {
        return createOrder(body, new QueryParams());
    }

    /**
     * Using the Orders API is the preferred approach when integrating the Mollie API into e-commerce applications such as webshops. If you want to use pay after delivery methods such as Klarna Pay later, using the Orders API is mandatory.
     * <p>
     * Creating an order will automatically create the required payment to allow your customer to pay for the order.
     * <p>
     * Once you have created an order, you should redirect your customer to the URL in the _links.checkout property from the response.
     * <p>
     * Note that when the payment fails, expires or is canceled, you can create a new payment using the Create order payment API. This is only possible for orders that have a created status.
     *
     * @param body   OrderRequest object
     * @param params A map of query params
     * @return OrderResponse object
     * @throws MollieException when something went wrong
     */
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

    /**
     * Retrieve a single order by its ID.
     *
     * @param orderId An order ID
     * @return OrderResponse object
     * @throws MollieException when something went wrong
     */
    public OrderResponse getOrder(String orderId) throws MollieException {
        return getOrder(orderId, new QueryParams());
    }

    /**
     * Retrieve a single order by its ID.
     *
     * @param orderId An order ID
     * @param params  A map of query params
     * @return OrderResponse object
     * @throws MollieException when something went wrong
     */
    public OrderResponse getOrder(String orderId, QueryParams params) throws MollieException {
        try {
            String uri = "/orders/" + orderId;

            HttpResponse<String> response = get(uri, params, true);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), OrderResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Retrieve all orders.
     *
     * @return Pagination<OrderListResponse> object
     * @throws MollieException when something went wrong
     */
    public Pagination<OrderListResponse> getOrders() throws MollieException {
        return getOrders(new QueryParams());
    }

    /**
     * Retrieve all orders.
     *
     * @param params A map of query params
     * @return Pagination<OrderListResponse> object
     * @throws MollieException when something went wrong
     */
    public Pagination<OrderListResponse> getOrders(QueryParams params) throws MollieException {
        try {
            String uri = "/orders";

            HttpResponse<String> response = get(uri, params, true);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<Pagination<OrderListResponse>>() {
                    });
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * This endpoint can be used to update the billing and/or shipping address of an order.
     * <p>
     * When updating an order that uses a pay after delivery method such as Klarna Pay later, Klarna may decline the requested changes, resulting in an error response from the Mollie API. The order remains intact, though the requested changes are not persisted.
     *
     * @param orderId An order ID
     * @param body    OrderUpdateRequest object
     * @return OrderResponse object
     * @throws MollieException when something went wrong
     */
    public OrderResponse updateOrder(String orderId, OrderUpdateRequest body) throws MollieException {
        return updateOrder(orderId, body, new QueryParams());
    }

    /**
     * This endpoint can be used to update the billing and/or shipping address of an order.
     * <p>
     * When updating an order that uses a pay after delivery method such as Klarna Pay later, Klarna may decline the requested changes, resulting in an error response from the Mollie API. The order remains intact, though the requested changes are not persisted.
     *
     * @param orderId An order ID
     * @param body    OrderUpdateRequest object
     * @param params  A map of query params
     * @return OrderResponse object
     * @throws MollieException when something went wrong
     */
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

    /**
     * This endpoint can be used to update an order line. Only the lines that belong to an order with status created, pending or authorized can be updated.
     * <p>
     * Use cases for this endpoint could be updating the name, productUrl and imageUrl for a certain order line because your customer wants to swap the item for a different variant, for example exchanging a blue skirt for the same skirt in red.
     * <p>
     * Or update the quantity, unitPrice, discountAmount, totalAmount, vatAmount and vatRate if you want to substitute a product for an entirely different one.
     * <p>
     * Alternatively, you can also (partially) cancel order lines instead of updating the quantity.
     * <p>
     * When updating an order line that uses a pay after delivery method such as Klarna Pay later, Klarna may decline the requested changes, resulting in an error response from the Mollie API. The order remains intact, though the requested changes are not persisted.
     *
     * @param orderId An order ID
     * @param lineId  A line ID
     * @param body    OrderLineUpdateRequest object
     * @return OrderResponse object
     * @throws MollieException when something went wrong
     */
    public OrderResponse updateOrderLine(String orderId, String lineId, OrderLineUpdateRequest body)
            throws MollieException {
        return updateOrderLine(orderId, lineId, body, new QueryParams());
    }

    /**
     * This endpoint can be used to update an order line. Only the lines that belong to an order with status created, pending or authorized can be updated.
     * <p>
     * Use cases for this endpoint could be updating the name, productUrl and imageUrl for a certain order line because your customer wants to swap the item for a different variant, for example exchanging a blue skirt for the same skirt in red.
     * <p>
     * Or update the quantity, unitPrice, discountAmount, totalAmount, vatAmount and vatRate if you want to substitute a product for an entirely different one.
     * <p>
     * Alternatively, you can also (partially) cancel order lines instead of updating the quantity.
     * <p>
     * When updating an order line that uses a pay after delivery method such as Klarna Pay later, Klarna may decline the requested changes, resulting in an error response from the Mollie API. The order remains intact, though the requested changes are not persisted.
     *
     * @param orderId An order ID
     * @param lineId  A line ID
     * @param body    OrderLineUpdateRequest object
     * @param params  A map of query params
     * @return OrderResponse object
     * @throws MollieException when something went wrong
     */
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

    /**
     * The order can only be canceled while:
     * <ul>
     * <li>the order doesn’t have any open payments except for the methods banktransfer, directdebit, klarnapaylater, and klarnasliceit.</li>
     * <li>the order’s status field is either created, authorized or shipping.</li>
     * <li>In case of created, all order lines will be canceled and the new order status will be canceled.</li>
     * <li>In case of authorized, the authorization will be released, all order lines will be canceled and the new order status will be canceled.</li>
     * <li>In case of shipping, any order lines that are still authorized will be canceled and order lines that are shipping will be completed. The new order status will be completed.</li>
     * </ul>
     *
     * @param orderId An order ID
     * @return OrderResponse object
     * @throws MollieException when something went wrong
     */
    public OrderResponse cancelOrder(String orderId) throws MollieException {
        return cancelOrder(orderId, new QueryParams());
    }

    /**
     * The order can only be canceled while:
     * <ul>
     * <li>the order doesn’t have any open payments except for the methods banktransfer, directdebit, klarnapaylater, and klarnasliceit.</li>
     * <li>the order’s status field is either created, authorized or shipping.</li>
     * <li>In case of created, all order lines will be canceled and the new order status will be canceled.</li>
     * <li>In case of authorized, the authorization will be released, all order lines will be canceled and the new order status will be canceled.</li>
     * <li>In case of shipping, any order lines that are still authorized will be canceled and order lines that are shipping will be completed. The new order status will be completed.</li>
     * </ul>
     *
     * @param orderId An order ID
     * @param params  A map of query params
     * @return OrderResponse object
     * @throws MollieException when something went wrong
     */
    public OrderResponse cancelOrder(String orderId, QueryParams params) throws MollieException {
        try {
            String uri = "/orders/" + orderId;

            HttpResponse<String> response = delete(uri, params, true);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), OrderResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    public OrderResponse manageOrderLines(String orderId, ManageOrderLineRequest request) throws MollieException {
        return manageOrderLines(orderId, request, new QueryParams());
    }

    private OrderResponse manageOrderLines(String orderId, ManageOrderLineRequest request, QueryParams params)
            throws MollieException {
        try {
            String uri = "/orders/" + orderId + "/lines";

            HttpResponse<String> response = patch(uri, request, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), OrderResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * This endpoint can be used to cancel one or more order lines that were previously authorized using a pay after delivery payment method. Use the Cancel Order API if you want to cancel the entire order or the remainder of the order.
     * <p>
     * Canceling or partially canceling an order line will immediately release the authorization held for that amount. Your customer will be able to see the updated order in his portal / app. Any canceled lines will be removed from the customer’s point of view, but will remain visible in the Mollie Dashboard.
     * <p>
     * You should cancel an order line if you don’t intend to (fully) ship it.
     * <p>
     * An order line can only be canceled while its status field is either authorized or shipping. If you cancel an authorized order line, the new order line status will be canceled. Canceling a shipping order line will result in a completed order line status.
     * <p>
     * If the order line is paid or already completed, you should create a refund using the Create Order Refund API instead.
     *
     * @param orderId An order ID
     * @param body    CancelOrderLinesRequest object
     * @throws MollieException when something went wrong
     */
    public void cancelOrderLines(String orderId, CancelOrderLinesRequest body) throws MollieException {
        cancelOrderLines(orderId, body, new QueryParams());
    }

    /**
     * This endpoint can be used to cancel one or more order lines that were previously authorized using a pay after delivery payment method. Use the Cancel Order API if you want to cancel the entire order or the remainder of the order.
     * <p>
     * Canceling or partially canceling an order line will immediately release the authorization held for that amount. Your customer will be able to see the updated order in his portal / app. Any canceled lines will be removed from the customer’s point of view, but will remain visible in the Mollie Dashboard.
     * <p>
     * You should cancel an order line if you don’t intend to (fully) ship it.
     * <p>
     * An order line can only be canceled while its status field is either authorized or shipping. If you cancel an authorized order line, the new order line status will be canceled. Canceling a shipping order line will result in a completed order line status.
     * <p>
     * If the order line is paid or already completed, you should create a refund using the Create Order Refund API instead.
     *
     * @param orderId An order ID
     * @param body    CancelOrderLinesRequest object
     * @param params  A map of query params
     * @throws MollieException when something went wrong
     */
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

    /**
     * An order has an automatically created payment that your customer can use to pay for the order. When the payment expires you can create a new payment for the order using this endpoint.
     * <p>
     * A new payment can only be created while the status of the order is created, and when the status of the existing payment is either expired, canceled or failed.
     * <p>
     * Note that order details (for example amount or webhookUrl) can not be changed using this endpoint.
     *
     * @param orderId An order ID
     * @param body    OrderPaymentRequest object
     * @return PaymentResponse object
     * @throws MollieException when something went wrong
     */
    public PaymentResponse createOrderPayment(String orderId, OrderPaymentRequest body) throws MollieException {
        return createOrderPayment(orderId, body, new QueryParams());
    }

    /**
     * An order has an automatically created payment that your customer can use to pay for the order. When the payment expires you can create a new payment for the order using this endpoint.
     * <p>
     * A new payment can only be created while the status of the order is created, and when the status of the existing payment is either expired, canceled or failed.
     * <p>
     * Note that order details (for example amount or webhookUrl) can not be changed using this endpoint.
     *
     * @param orderId An order ID
     * @param body    OrderPaymentRequest object
     * @param params  A map of query params
     * @return PaymentResponse object
     * @throws MollieException when something went wrong
     */
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

    /**
     * When using the Orders API, refunds should be made against the order. When using pay after delivery payment methods such as Klarna Pay later and Klarna Slice it, this ensures that your customer will receive credit invoices with the correct product information on them and have a great experience.
     * <p>
     * However, if you want to refund arbitrary amounts you can use the Create Payment Refund API for Pay later and Slice it.
     * <p>
     * If an order line is still in the authorized status, it cannot be refunded. You should cancel it instead. Order lines that are paid, shipping or completed can be refunded.
     *
     * @param orderId An order ID
     * @param body    OrderRefundRequest object
     * @return RefundResponse object
     * @throws MollieException when something went wrong
     */
    public RefundResponse createOrderRefund(String orderId, OrderRefundRequest body) throws MollieException {
        return createOrderRefund(orderId, body, new QueryParams());
    }

    /**
     * When using the Orders API, refunds should be made against the order. When using pay after delivery payment methods such as Klarna Pay later and Klarna Slice it, this ensures that your customer will receive credit invoices with the correct product information on them and have a great experience.
     * <p>
     * However, if you want to refund arbitrary amounts you can use the Create Payment Refund API for Pay later and Slice it.
     * <p>
     * If an order line is still in the authorized status, it cannot be refunded. You should cancel it instead. Order lines that are paid, shipping or completed can be refunded.
     *
     * @param orderId An order ID
     * @param body    OrderRefundRequest object
     * @param params  A map of query params
     * @return RefundResponse object
     * @throws MollieException when something went wrong
     */
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

    /**
     * Retrieve all order refunds.
     *
     * @param orderId An order ID
     * @return Pagination<OrderRefundListResponse> object
     * @throws MollieException when something went wrong
     */
    public Pagination<OrderRefundListResponse> getOrderRefunds(String orderId) throws MollieException {
        return getOrderRefunds(orderId, new QueryParams());
    }

    /**
     * Retrieve all order refunds.
     *
     * @param orderId An order ID
     * @param params  A map of query params
     * @return Pagination<OrderRefundListResponse> object
     * @throws MollieException when something went wrong
     */
    public Pagination<OrderRefundListResponse> getOrderRefunds(String orderId, QueryParams params)
            throws MollieException {
        try {
            String uri = "/orders/" + orderId + "/refunds";

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<Pagination<OrderRefundListResponse>>() {
                    });
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }
}
