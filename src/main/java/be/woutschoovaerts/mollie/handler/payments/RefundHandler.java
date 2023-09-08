package be.woutschoovaerts.mollie.handler.payments;

import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.data.refund.RefundListResponse;
import be.woutschoovaerts.mollie.data.refund.RefundRequest;
import be.woutschoovaerts.mollie.data.refund.RefundResponse;
import be.woutschoovaerts.mollie.exception.MollieException;
import be.woutschoovaerts.mollie.util.QueryParams;
import be.woutschoovaerts.mollie.util.RestService;
import com.fasterxml.jackson.core.type.TypeReference;
import kong.unirest.UnirestException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Handles the Refund API <a href="https://docs.mollie.com/reference/v2/refunds-api/create-refund">Mollie docs</a>
 *
 * @author Wout Schoovaerts
 */
@RequiredArgsConstructor
public class RefundHandler {

    private static final Logger log = LoggerFactory.getLogger(RefundHandler.class);

    private static final TypeReference<RefundResponse> REFUND_RESPONSE_TYPE = new TypeReference<>() {
    };
    private static final TypeReference<Pagination<RefundListResponse>> REFUND_LIST_RESPONSE_TYPE = new TypeReference<>() {
    };

    private final RestService restService;

    /**
     * Most payment methods support refunds. This means you can request your payment to be refunded to your customer. The refunded amount will be withheld from your next settlement.
     * <p>
     * Refunds are not available at all for Bitcoin, paysafecard and gift cards. If you need to refund direct debit payments, please contact our support department.
     * <p>
     * Refunds support descriptions, which we will show in the Dashboard, your exports and pass to your customer if possible.
     * <p>
     * If you have insufficient balance with Mollie to perform the refund, the refund will be queued. We will automatically process the refund once your balance increases.
     * <p>
     * Any payments created for Orders can also be refunded using the Payment Refunds API. However, we recommend using the Order Refund API in those cases so you can pass the order lines you are refunding too. For pay after delivery methods, this is mandatory.
     * <p>
     * Possible errors¶
     * Sometimes a situation can occur in which it is not possible to perform the refund. In such cases an HTTP 4xx error will be returned. Some of these situations are illustrated here:
     * <p>
     * There might not be enough balance on your account with the payment provider (e.g. PayPal).
     * You may have forgotten to grant the appropriate rights to Mollie for the payment provider (PayPal only).
     * It is possible that the payment has already been (partially) refunded.
     * It is not always possible to do a partial refund.
     * If you perform many refunds in parallel, you may get an HTTP 503 Service unavailable error. In this case, you can be certain the refund was not performed and you can safely retry the refund.
     * <p>
     * If there is a connection issue during the creation of a refund (e.g. a client-side time out is triggered) you should not retry automatically as you cannot be sure the refund has been performed or not. In this case we suggest logging into the Mollie Dashboard, or retrieving the payment’s refunds via the API to validate if the refund has been created.
     *
     * @param paymentId the payment id for the payment that needs to be refunded
     * @param body      RefundRequest object
     * @return RefundResponse object
     * @throws MollieException when something went wrong
     */
    public RefundResponse createRefund(String paymentId, RefundRequest body) throws MollieException {
        return createRefund(paymentId, body, new QueryParams());
    }

    /**
     * Most payment methods support refunds. This means you can request your payment to be refunded to your customer. The refunded amount will be withheld from your next settlement.
     * <p>
     * Refunds are not available at all for Bitcoin, paysafecard and gift cards. If you need to refund direct debit payments, please contact our support department.
     * <p>
     * Refunds support descriptions, which we will show in the Dashboard, your exports and pass to your customer if possible.
     * <p>
     * If you have insufficient balance with Mollie to perform the refund, the refund will be queued. We will automatically process the refund once your balance increases.
     * <p>
     * Any payments created for Orders can also be refunded using the Payment Refunds API. However, we recommend using the Order Refund API in those cases so you can pass the order lines you are refunding too. For pay after delivery methods, this is mandatory.
     * <p>
     * Possible errors¶
     * Sometimes a situation can occur in which it is not possible to perform the refund. In such cases an HTTP 4xx error will be returned. Some of these situations are illustrated here:
     * <p>
     * There might not be enough balance on your account with the payment provider (e.g. PayPal).
     * You may have forgotten to grant the appropriate rights to Mollie for the payment provider (PayPal only).
     * It is possible that the payment has already been (partially) refunded.
     * It is not always possible to do a partial refund.
     * If you perform many refunds in parallel, you may get an HTTP 503 Service unavailable error. In this case, you can be certain the refund was not performed and you can safely retry the refund.
     * <p>
     * If there is a connection issue during the creation of a refund (e.g. a client-side time out is triggered) you should not retry automatically as you cannot be sure the refund has been performed or not. In this case we suggest logging into the Mollie Dashboard, or retrieving the payment’s refunds via the API to validate if the refund has been created.
     *
     * @param paymentId the payment id for the payment that needs to be refunded
     * @param body      RefundRequest object
     * @param params    A map of query params
     * @return RefundResponse object
     * @throws MollieException when something went wrong
     */
    public RefundResponse createRefund(String paymentId, RefundRequest body, QueryParams params)
            throws MollieException {
        try {
            String uri = "/payments/" + paymentId + "/refunds";

            return restService.post(uri, body, params, REFUND_RESPONSE_TYPE);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Retrieve a single refund by its ID. Note the original payment’s ID is needed as well.
     *
     * @param paymentId the payment id for of the refund
     * @param refundId  the refund id
     * @return RefundResponse object
     * @throws MollieException when something went wrong
     */
    public RefundResponse getRefund(String paymentId, String refundId) throws MollieException {
        return getRefund(paymentId, refundId, new QueryParams());
    }

    /**
     * Retrieve a single refund by its ID. Note the original payment’s ID is needed as well.
     *
     * @param paymentId the payment id for of the refund
     * @param refundId  the refund id
     * @param params    A map of query params
     * @return RefundResponse object
     * @throws MollieException when something went wrong
     */
    public RefundResponse getRefund(String paymentId, String refundId, QueryParams params) throws MollieException {
        try {
            String uri = "/payments/" + paymentId + "/refunds/" + refundId;

            return restService.get(uri, params, true, REFUND_RESPONSE_TYPE);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }


    /**
     * For certain payment methods, like iDEAL, the underlying banking system will delay refunds until the next day. Until that time, refunds may be canceled manually in your Mollie account, or automatically by using this endpoint.
     * <p>
     * The refund can only be canceled while the refund’s status field is either queued or pending. See Get payment refund for more information.
     *
     * @param paymentId the payment id off the refund
     * @param refundId  the refund id
     * @throws MollieException when something went wrong
     */
    public void cancelRefund(String paymentId, String refundId) throws MollieException {
        cancelRefund(paymentId, refundId, new QueryParams());
    }

    /**
     * For certain payment methods, like iDEAL, the underlying banking system will delay refunds until the next day. Until that time, refunds may be canceled manually in your Mollie account, or automatically by using this endpoint.
     * <p>
     * The refund can only be canceled while the refund’s status field is either queued or pending. See Get payment refund for more information.
     *
     * @param paymentId the payment id off the refund
     * @param refundId  the refund id
     * @param params    A map of query params
     * @throws MollieException when something went wrong
     */
    public void cancelRefund(String paymentId, String refundId, QueryParams params) throws MollieException {
        try {
            String uri = "/payments/" + paymentId + "/refunds/" + refundId;

            restService.delete(uri, params, true, new TypeReference<Void>(){});
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Retrieve refunds.
     * <p>
     * only refunds for the corresponding website profile and mode are returned.
     * <p>
     * The results are paginated
     *
     * @return paginated response of RefundResponse objects
     * @throws MollieException when something went wrong
     */
    public Pagination<RefundListResponse> listRefunds() throws MollieException {
        return listRefunds(new QueryParams());
    }

    /**
     * Retrieve refunds.
     * <p>
     * only refunds for the corresponding website profile and mode are returned.
     * <p>
     * The results are paginated
     *
     * @param params A map of query params
     * @return paginated response of RefundResponse objects
     * @throws MollieException when something went wrong
     */
    public Pagination<RefundListResponse> listRefunds(QueryParams params) throws MollieException {
        try {
            String uri = "/refunds";

            return restService.get(uri, params, true, REFUND_LIST_RESPONSE_TYPE);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }


    /**
     * Retrieve refunds.
     * <p>
     * only refunds for that specific payment are returned.
     * <p>
     * The results are paginated
     *
     * @param paymentId a payment id
     * @return paginated response of RefundResponse objects
     * @throws MollieException when something went wrong
     */
    public Pagination<RefundListResponse> listRefunds(String paymentId) throws MollieException {
        return listRefunds(paymentId, new QueryParams());
    }

    /**
     * Retrieve refunds.
     * <p>
     * only refunds for that specific payment are returned.
     * <p>
     * The results are paginated
     *
     * @param paymentId a payment id
     * @param params    A map of query params
     * @return paginated response of RefundResponse objects
     * @throws MollieException when something went wrong
     */
    public Pagination<RefundListResponse> listRefunds(String paymentId, QueryParams params) throws MollieException {
        try {
            String uri = "/payments/" + paymentId + "/refunds";

            return restService.get(uri, params, true, REFUND_LIST_RESPONSE_TYPE);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }
}
