package be.feelio.mollie.handler;

import be.feelio.mollie.exception.MollieException;
import be.feelio.mollie.json.common.Pagination;
import be.feelio.mollie.json.request.MandateRequest;
import be.feelio.mollie.json.response.MandateListResponse;
import be.feelio.mollie.json.response.MandateResponse;
import be.feelio.mollie.util.ObjectMapperService;
import be.feelio.mollie.util.QueryParams;
import com.fasterxml.jackson.core.type.TypeReference;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Handles the Mandates API <a href="https://docs.mollie.com/reference/v2/mandates-api/create-mandate">Mollie docs</a>
 *
 * @author Wout Schoovaerts
 */
public class MandateHandler extends AbstractHandler {

    private static final Logger log = LoggerFactory.getLogger(MandateHandler.class);

    public MandateHandler(String baseUrl) {
        super(baseUrl, log);
    }

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
        return createMandate(customerId, body, QueryParams.EMPTY);
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
        try {
            String uri = "/customers/" + customerId + "/mandates";

            HttpResponse<String> response = post(uri, body, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), MandateResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
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
        return getMandate(customerId, mandateId, QueryParams.EMPTY);
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
        try {
            String uri = "/customers/" + customerId + "/mandates/" + mandateId;

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), MandateResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Revoke a customer’s mandate. You will no longer be able to charge the consumer’s bank account or credit card with this mandate.
     *
     * @param customerId a customer id
     * @param mandateId  a mandate id
     * @throws MollieException when something went wrong
     */
    public void revokeMandate(String customerId, String mandateId) throws MollieException {
        revokeMandate(customerId, mandateId, QueryParams.EMPTY);
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
        try {
            String uri = "/customers/" + customerId + "/mandates/" + mandateId;

            HttpResponse<String> response = delete(uri, params);

        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
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
        return listMandates(customerId, QueryParams.EMPTY);
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
        try {
            String uri = "/customers/" + customerId + "/mandates";

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<Pagination<MandateListResponse>>() {
                    });
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }
}
