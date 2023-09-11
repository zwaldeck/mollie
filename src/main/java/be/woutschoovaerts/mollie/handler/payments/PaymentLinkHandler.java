package be.woutschoovaerts.mollie.handler.payments;

import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.data.paymentLink.PaymentLinkListResponse;
import be.woutschoovaerts.mollie.data.paymentLink.PaymentLinkRequest;
import be.woutschoovaerts.mollie.data.paymentLink.PaymentLinkResponse;
import be.woutschoovaerts.mollie.exception.MollieException;
import be.woutschoovaerts.mollie.util.QueryParams;
import be.woutschoovaerts.mollie.util.RestService;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;


/**
 * Handles the Payment links API <a href="https://docs.mollie.com/reference/v2/payment-links-api">Mollie docs</a>
 */
@RequiredArgsConstructor
public class PaymentLinkHandler {

    private static final TypeReference<PaymentLinkResponse> PAYMENT_LINK_RESPONSE_TYPE = new TypeReference<>() {
    };
    private static final TypeReference<Pagination<PaymentLinkListResponse>> PAYMENT_LINK_LIST_RESPONSE_TYPE = new TypeReference<>() {
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
            String uri = "/payment-links";

            return restService.post(uri, body, params, PAYMENT_LINK_RESPONSE_TYPE);
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
            String uri = "/payment-links/" + paymentLinkId;

            return restService.get(uri, params, true, PAYMENT_LINK_RESPONSE_TYPE);
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
            String uri = "/payment-links";
            return restService.get(uri, params, true, PAYMENT_LINK_LIST_RESPONSE_TYPE);
    }
}
