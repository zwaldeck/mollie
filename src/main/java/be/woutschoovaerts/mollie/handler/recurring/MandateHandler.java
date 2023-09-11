package be.woutschoovaerts.mollie.handler.recurring;

import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.data.mandate.MandateListResponse;
import be.woutschoovaerts.mollie.data.mandate.MandateRequest;
import be.woutschoovaerts.mollie.data.mandate.MandateResponse;
import be.woutschoovaerts.mollie.exception.MollieException;
import be.woutschoovaerts.mollie.util.QueryParams;
import be.woutschoovaerts.mollie.util.RestService;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;

/**
 * Handles the Mandates API <a href="https://docs.mollie.com/reference/v2/mandates-api/create-mandate">Mollie docs</a>
 *
 * @author Wout Schoovaerts
 */
@RequiredArgsConstructor
public class MandateHandler {

    private static final TypeReference<MandateResponse> MANDATE_RESPONSE_TYPE = new TypeReference<>() {
    };
    private static final TypeReference<Pagination<MandateListResponse>> MANDATE_LIST_RESPONSE_TYPE = new TypeReference<>() {
    };

    private final RestService restService;

    /**
     * Create a mandate for a specific customer. Mandates allow you to charge a customer’s credit card or bank account recurrently.
     * <p>
     * It is only possible to create mandates for IBANs with this endpoint. To create mandates for credit cards, have your customers perform a ‘first payment’ with their credit card.
     *
     * @param customerId a customer id
     * @param body       MandateRequest object
     * @return MandateResponse object
     * @throws MollieException when something went wrong
     */
    public MandateResponse createMandate(String customerId, MandateRequest body) throws MollieException {
        return createMandate(customerId, body, new QueryParams());
    }

    /**
     * Create a mandate for a specific customer. Mandates allow you to charge a customer’s credit card or bank account recurrently.
     * <p>
     * It is only possible to create mandates for IBANs with this endpoint. To create mandates for credit cards, have your customers perform a ‘first payment’ with their credit card.
     *
     * @param customerId a customer id
     * @param body       MandateRequest object
     * @param params     A map of query parameters
     * @return MandateResponse object
     * @throws MollieException when something went wrong
     */
    public MandateResponse createMandate(String customerId, MandateRequest body, QueryParams params)
            throws MollieException {
            String uri = "/customers/" + customerId + "/mandates";

            return restService.post(uri, body, params, MANDATE_RESPONSE_TYPE);
    }

    /**
     * Retrieve a mandate by its ID and its customer’s ID. The mandate will either contain IBAN or credit card details, depending on the type of mandate.
     *
     * @param customerId a customer id
     * @param mandateId  a mandate id
     * @return MandateResponse object
     * @throws MollieException when something went wrong
     */
    public MandateResponse getMandate(String customerId, String mandateId) throws MollieException {
        return getMandate(customerId, mandateId, new QueryParams());
    }

    /**
     * Retrieve a mandate by its ID and its customer’s ID. The mandate will either contain IBAN or credit card details, depending on the type of mandate.
     *
     * @param customerId a customer id
     * @param mandateId  a mandate id
     * @param params     A map of query parameters
     * @return MandateResponse object
     * @throws MollieException when something went wrong
     */
    public MandateResponse getMandate(String customerId, String mandateId, QueryParams params)
            throws MollieException {
            String uri = "/customers/" + customerId + "/mandates/" + mandateId;

            return restService.get(uri, params, true, MANDATE_RESPONSE_TYPE);
    }

    /**
     * Revoke a customer’s mandate. You will no longer be able to charge the consumer’s bank account or credit card with this mandate.
     *
     * @param customerId a customer id
     * @param mandateId  a mandate id
     * @throws MollieException when something went wrong
     */
    public void revokeMandate(String customerId, String mandateId) throws MollieException {
        revokeMandate(customerId, mandateId, new QueryParams());
    }

    /**
     * Revoke a customer’s mandate. You will no longer be able to charge the consumer’s bank account or credit card with this mandate.
     *
     * @param customerId a customer id
     * @param mandateId  a mandate id
     * @param params     A map of query parameters
     * @throws MollieException when something went wrong
     */
    public void revokeMandate(String customerId, String mandateId, QueryParams params)
            throws MollieException {
            String uri = "/customers/" + customerId + "/mandates/" + mandateId;

            restService.delete(uri, params, true, new TypeReference<Void>() {});
    }

    /**
     * Retrieve all mandates for the given customerId, ordered from newest to oldest.
     * <p>
     * The results are paginated.
     *
     * @param customerId a customer id
     * @return paginated list of MandateResponse objects
     * @throws MollieException when something went wrong
     */
    public Pagination<MandateListResponse> listMandates(String customerId) throws MollieException {
        return listMandates(customerId, new QueryParams());
    }


    /**
     * Retrieve all mandates for the given customerId, ordered from newest to oldest.
     * <p>
     * The results are paginated.
     *
     * @param customerId a customer id
     * @param params     A map of query parameters
     * @return paginated list of MandateResponse objects
     * @throws MollieException when something went wrong
     */
    public Pagination<MandateListResponse> listMandates(String customerId, QueryParams params) throws MollieException {
            String uri = "/customers/" + customerId + "/mandates";

            return restService.get(uri, params, true, MANDATE_LIST_RESPONSE_TYPE);
    }
}
