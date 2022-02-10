package be.woutschoovaerts.mollie.handler;

import be.woutschoovaerts.mollie.data.organization.OrganizationResponse;
import be.woutschoovaerts.mollie.exception.MollieException;
import be.woutschoovaerts.mollie.util.Config;
import be.woutschoovaerts.mollie.util.QueryParams;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles the organizations API <a href="https://docs.mollie.com/reference/v2/organizations-api/current-organization">Mollie docs</a>
 *
 * @author Wout Schoovaerts
 */
public class OrganizationHandler extends AbstractHandler {

    private static final Logger log = LoggerFactory.getLogger(OrganizationHandler.class);

    public OrganizationHandler(String baseUrl, Config config) {
        super(baseUrl, log, config);
    }

    /**
     * Retrieve the currently authenticated organization.
     *
     * @return OrganizationResponse object
     * @throws MollieException when something went wrong
     */
    public OrganizationResponse getMyOrganization() throws MollieException {
        return getMyOrganization(QueryParams.EMPTY);
    }

    /**
     * Retrieve the currently authenticated organization.
     *
     * @param params a map of query params
     * @return OrganizationResponse object
     * @throws MollieException when something went wrong
     */
    public OrganizationResponse getMyOrganization(QueryParams params) throws MollieException {
        String uri = "/organizations/me";

        return get(uri, params, new TypeReference<>() {
        });
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
        return getOrganization(organizationId, QueryParams.EMPTY);
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
        String uri = "/organizations/" + organizationId;

        return get(uri, params, new TypeReference<>() {
        });
    }
}
