package be.woutschoovaerts.mollie.handler;

import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.data.paymentLink.PaymentLinkListResponse;
import be.woutschoovaerts.mollie.data.paymentLink.PaymentLinkRequest;
import be.woutschoovaerts.mollie.data.paymentLink.PaymentLinkResponse;
import be.woutschoovaerts.mollie.exception.MollieException;
import com.fasterxml.jackson.core.type.TypeReference;
import be.woutschoovaerts.mollie.util.Config;
import be.woutschoovaerts.mollie.util.ObjectMapperService;
import be.woutschoovaerts.mollie.util.QueryParams;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


/**
 * Handles the Payment links API <a href="https://docs.mollie.com/reference/v2/payment-links-api">Mollie docs</a>
 *
 * @author Niek Knuiman
 */
public class PaymentLinkHandler extends AbstractHandler {

    private static final Logger log = LoggerFactory.getLogger(PaymentLinkHandler.class);

    private static final String PAYMENT_LINK_RESOURCE = "/payment-links";

    public PaymentLinkHandler(String baseUrl, Config config) {
        super(baseUrl, log, config);
    }

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
            HttpResponse<String> response = post(PAYMENT_LINK_RESOURCE, body, params);

            return ObjectMapperService.getInstance().getMapper()
                    .readValue(response.getBody(), PaymentLinkResponse.class);
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
            String uri = PAYMENT_LINK_RESOURCE + "/" + paymentLinkId;

            HttpResponse<String> response = get(uri, params, true);

            return ObjectMapperService.getInstance().getMapper()
                    .readValue(response.getBody(), PaymentLinkResponse.class);
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
            HttpResponse<String> response = get(PAYMENT_LINK_RESOURCE, params, true);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<Pagination<PaymentLinkListResponse>>() {
                    });
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }
}
