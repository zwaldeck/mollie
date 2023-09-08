package be.woutschoovaerts.mollie.handler.connect;

import be.woutschoovaerts.mollie.data.organization.OrganizationPartnerResponse;
import be.woutschoovaerts.mollie.data.organization.OrganizationResponse;
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
 * Handles the organizations API <a href="https://docs.mollie.com/reference/v2/organizations-api/current-organization">Mollie docs</a>
 *
 * @author Wout Schoovaerts
 */
@RequiredArgsConstructor
public class OrganizationHandler {

    private static final Logger log = LoggerFactory.getLogger(OrganizationHandler.class);

    private static final TypeReference<OrganizationResponse> ORGANIZATION_RESPONSE_TYPE = new TypeReference<>() {
    };
    private static final TypeReference<OrganizationPartnerResponse> ORGANIZATION_PARTNER_RESPONSE_TYPE = new TypeReference<>() {
    };


    private final RestService restService;

    /**
     * Retrieve the currently authenticated organization.
     *
     * @return OrganizationResponse object
     * @throws MollieException when something went wrong
     */
    public OrganizationResponse getMyOrganization() throws MollieException {
        return getMyOrganization(new QueryParams());
    }

    /**
     * Retrieve the currently authenticated organization.
     *
     * @param params a map of query params
     * @return OrganizationResponse object
     * @throws MollieException when something went wrong
     */
    public OrganizationResponse getMyOrganization(QueryParams params) throws MollieException {
        try {
            String uri = "/organizations/me";

            return restService.get(uri, params, false, ORGANIZATION_RESPONSE_TYPE);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Retrieve an organization by its ID.
     * <p>
     * If you do not know the organization’s ID, you can use the organizations list endpoint to retrieve all organizations that are accessible.
     *
     * @param organizationId An organization ID
     * @return OrganizationResponse object
     * @throws MollieException when something went wrong
     */
    public OrganizationResponse getOrganization(String organizationId) throws MollieException {
        return getOrganization(organizationId, new QueryParams());
    }

    /**
     * Retrieve an organization by its ID.
     * <p>
     * If you do not know the organization’s ID, you can use the organizations list endpoint to retrieve all organizations that are accessible.
     *
     * @param organizationId An organization ID
     * @param params         a map of query params
     * @return OrganizationResponse object
     * @throws MollieException when something went wrong
     */
    public OrganizationResponse getOrganization(String organizationId, QueryParams params) throws MollieException {
        try {
            String uri = "/organizations/" + organizationId;

            return restService.get(uri, params, false, ORGANIZATION_RESPONSE_TYPE);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Retrieve details about the partner status of the currently authenticated organization.
     *
     * @return OrganizationPartnerResponse object
     * @throws MollieException when something went wrong
     */
    public OrganizationPartnerResponse getPartner() throws MollieException {
        return getPartner(new QueryParams());
    }

    /**
     * Retrieve details about the partner status of the currently authenticated organization.
     *
     * @param params         a map of query params
     * @return OrganizationPartnerResponse object
     * @throws MollieException when something went wrong
     */
    public OrganizationPartnerResponse getPartner(QueryParams params) throws MollieException {
        try {
            String uri = "/organizations/me/partner";

            return restService.get(uri, params, false, ORGANIZATION_PARTNER_RESPONSE_TYPE);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }
}
