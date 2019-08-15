package be.feelio.mollie.handler;

import be.feelio.mollie.data.organization.OrganizationResponse;
import be.feelio.mollie.exception.MollieException;
import be.feelio.mollie.util.ObjectMapperService;
import be.feelio.mollie.util.QueryParams;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class OrganizationHandler extends AbstractHandler {

    private static final Logger log = LoggerFactory.getLogger(OrganizationHandler.class);

    public OrganizationHandler(String baseUrl) {
        super(baseUrl, log);
    }

    public OrganizationResponse getMyOrganization() throws MollieException {
        return getMyOrganization(QueryParams.EMPTY);
    }

    public OrganizationResponse getMyOrganization(QueryParams params) throws MollieException {
        try {
            String uri = "/organizations/me";

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper()
                    .readValue(response.getBody(), OrganizationResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    public OrganizationResponse getOrganization(String organizationId) throws MollieException {
        return getOrganization(organizationId, QueryParams.EMPTY);
    }

    public OrganizationResponse getOrganization(String organizationId, QueryParams params) throws MollieException {
        try {
            String uri = "/organizations/" + organizationId;

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper()
                    .readValue(response.getBody(), OrganizationResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }
}
