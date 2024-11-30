package be.woutschoovaerts.mollie.handler.payments;

import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.data.payment.PaymentListResponse;
import be.woutschoovaerts.mollie.data.paymentLink.PaymentLinkListResponse;
import be.woutschoovaerts.mollie.data.paymentLink.PaymentLinkRequest;
import be.woutschoovaerts.mollie.data.paymentLink.PaymentLinkResponse;
import be.woutschoovaerts.mollie.data.paymentLink.UpdatePaymentLinkRequest;
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
 * Handles the Payment links API <a href="https://docs.mollie.com/reference/v2/payment-links-api">Mollie docs</a>
 */
@RequiredArgsConstructor
public class PaymentLinkHandler {

    private static final Logger log = LoggerFactory.getLogger(PaymentLinkHandler.class);

    private static final TypeReference<PaymentLinkResponse> PAYMENT_LINK_RESPONSE_TYPE = new TypeReference<>() {
    };
    private static final TypeReference<Pagination<PaymentLinkListResponse>> PAYMENT_LINK_LIST_RESPONSE_TYPE = new TypeReference<>() {
    };
    private static final TypeReference<Pagination<PaymentListResponse>> PAYMENT_LIST_RESPONSE_TYPE = new TypeReference<>() {
    };

    private final RestService restService;

    /**
     * Generate payment links that by default, unlike regular payments, do not expire.
     *
     * @param body PaymentLinkRequest can be build with the builder pattern
     * @return The payment link response from mollie
     * @throws MollieException when something went wrong
     */
    public PaymentLinkResponse createPaymentLink(PaymentLinkRequest body) throws MollieException {
        return createPaymentLink(body, new QueryParams());
    }

    /**
     * Generate payment links that by default, unlike regular payments, do not expire.
     *
     * @param body   PaymentLinkRequest can be build with the builder pattern
     * @param params A map of query parameters
     * @return The payment link response from mollie
     * @throws MollieException when something went wrong
     */
    public PaymentLinkResponse createPaymentLink(PaymentLinkRequest body, QueryParams params) throws MollieException {
        try {
            String uri = "/payment-links";

            return restService.post(uri, body, params, PAYMENT_LINK_RESPONSE_TYPE);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Retrieve a single payment link object by its payment link token.
     *
     * @param paymentLinkId payment link token
     * @return The payment response from mollie
     * @throws MollieException when something went wrong
     */
    public PaymentLinkResponse getPaymentLink(String paymentLinkId) throws MollieException {
        return getPaymentLink(paymentLinkId, new QueryParams());
    }

    /**
     * Retrieve a single payment link object by its payment link token.
     *
     * @param paymentLinkId payment link token
     * @param params        A map of query parameters
     * @return The payment response from mollie
     * @throws MollieException when something went wrong
     */
    public PaymentLinkResponse getPaymentLink(String paymentLinkId, QueryParams params)
            throws MollieException {
        try {
            String uri = "/payment-links/" + paymentLinkId;

            return restService.get(uri, params, true, PAYMENT_LINK_RESPONSE_TYPE);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Retrieve all payment links created with the current website profile, ordered from newest to oldest.
     * <p>
     * The results are paginated.
     *
     * @return paginated payment response list
     * @throws MollieException when something went wrong
     */
    public Pagination<PaymentLinkListResponse> listPaymentLinks() throws MollieException {
        return listPaymentLinks(new QueryParams());
    }


    /**
     * Retrieve all payment links created with the current website profile, ordered from newest to oldest.
     * <p>
     * The results are paginated.
     *
     * @param params A map of query parameters
     * @return paginated payment response list
     * @throws MollieException when something went wrong
     */
    public Pagination<PaymentLinkListResponse> listPaymentLinks(QueryParams params) throws MollieException {
        try {
            String uri = "/payment-links";
            return restService.get(uri, params, true, PAYMENT_LINK_LIST_RESPONSE_TYPE);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Certain details of an existing payment link can be updated.
     *
     * @param paymentLinkId The payment link ID
     * @param request       The request body
     * @return Single payment link response
     * @throws MollieException when something went wrong
     */
    public PaymentLinkResponse updatePaymentLink(String paymentLinkId, UpdatePaymentLinkRequest request) throws MollieException {
        return updatePaymentLink(paymentLinkId, request, new QueryParams());
    }

    /**
     * Certain details of an existing payment link can be updated.
     *
     * @param paymentLinkId The payment link ID
     * @param request       The request body
     * @param params        The query params
     * @return Single payment link response
     * @throws MollieException when something went wrong
     */
    public PaymentLinkResponse updatePaymentLink(String paymentLinkId, UpdatePaymentLinkRequest request, QueryParams params) throws MollieException {
        try {
            String uri = "/payment-links/" + paymentLinkId;
            return restService.patch(uri, request, params, PAYMENT_LINK_RESPONSE_TYPE);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Payment links which have not been opened and no payments have been made yet can be deleted entirely. This can be useful for removing payment links that have been incorrectly configured or that are no longer relevant.
     * <p>
     * Once deleted, the payment link will no longer show up in the API or Mollie dashboard.
     * <p>
     * To simply disable a payment link without fully deleting it, you can use the archived parameter on the Update payment link endpoint instead.
     *
     * @param paymentLinkId The payment link ID
     * @throws MollieException when something went wrong
     */
    public void deletePaymentLink(String paymentLinkId) throws MollieException {
        deletePaymentLink(paymentLinkId, new QueryParams());
    }

    /**
     * Payment links which have not been opened and no payments have been made yet can be deleted entirely. This can be useful for removing payment links that have been incorrectly configured or that are no longer relevant.
     * <p>
     * Once deleted, the payment link will no longer show up in the API or Mollie dashboard.
     * <p>
     * To simply disable a payment link without fully deleting it, you can use the archived parameter on the Update payment link endpoint instead.
     *
     * @param paymentLinkId The payment link ID
     * @param params        The query params
     * @throws MollieException when something went wrong
     */
    public void deletePaymentLink(String paymentLinkId, QueryParams params) throws MollieException {
        try {
            String uri = "/payment-links/" + paymentLinkId;
            restService.delete(uri, params, new TypeReference<Void>() {
            });
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Retrieve the list of payments for a specific payment link.
     * <p>
     * The results are paginated.
     *
     * @param paymentLinkId The payment link ID
     * @return Paginated payments
     * @throws MollieException when something went wrong
     */
    public Pagination<PaymentListResponse> listPaymentLinkPayments(String paymentLinkId) throws MollieException {
        return listPaymentLinkPayments(paymentLinkId, new QueryParams());
    }

    /**
     * Retrieve the list of payments for a specific payment link.
     * <p>
     * The results are paginated.
     *
     * @param paymentLinkId The payment link ID
     * @param params        The query params
     * @return Paginated payments
     * @throws MollieException when something went wrong
     */
    public Pagination<PaymentListResponse> listPaymentLinkPayments(String paymentLinkId, QueryParams params) throws MollieException {
        try {
            String uri = "/payment-links/" + paymentLinkId + "/payments";
            return restService.get(uri, params, true, PAYMENT_LIST_RESPONSE_TYPE);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

}
