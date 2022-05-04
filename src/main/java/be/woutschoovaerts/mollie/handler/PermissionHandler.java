package be.woutschoovaerts.mollie.handler;

import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.data.permission.Permission;
import be.woutschoovaerts.mollie.data.permission.PermissionListResponse;
import be.woutschoovaerts.mollie.data.permission.PermissionResponse;
import be.woutschoovaerts.mollie.exception.MollieException;
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
 * Handles the Permissions API <a href="https://docs.mollie.com/reference/v2/permissions-api/get-permission">Mollie docs</a>
 *
 * @author Wout Schoovaerts
 */
public class PermissionHandler extends AbstractHandler {

    private static final Logger log = LoggerFactory.getLogger(PermissionHandler.class);

    public PermissionHandler(String baseUrl, Config config) {
        super(baseUrl, log, config);
    }

    /**
     * All API actions through OAuth are by default protected for privacy and/or money related reasons and therefore require specific permissions. These permissions can be requested by apps during the OAuth authorization flow. The Permissions resource allows the app to check whether an API action is (still) allowed by the authorization.
     *
     * @param permission A permission
     * @return PermissionResponse object
     * @throws MollieException when something went wrong
     */
    public PermissionResponse getPermission(Permission permission) throws MollieException {
        return getPermission(permission, new QueryParams());
    }

    /**
     * All API actions through OAuth are by default protected for privacy and/or money related reasons and therefore require specific permissions. These permissions can be requested by apps during the OAuth authorization flow. The Permissions resource allows the app to check whether an API action is (still) allowed by the authorization.
     *
     * @param permission A permission
     * @param params     A map of query params
     * @return PermissionResponse object
     * @throws MollieException when something went wrong
     */
    public PermissionResponse getPermission(Permission permission, QueryParams params) throws MollieException {
        try {
            String uri = "/permissions/" + permission.getValue();

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper()
                    .readValue(response.getBody(), PermissionResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * List all permissions available with the current app access token.
     *
     * @return Pagination<PermissionListResponse> object
     * @throws MollieException when something went wrong
     */
    public Pagination<PermissionListResponse> getPermissions() throws MollieException {
        return getPermissions(new QueryParams());
    }

    /**
     * List all permissions available with the current app access token.
     *
     * @param params A map of query params
     * @return Pagination<PermissionListResponse> object
     * @throws MollieException when something went wrong
     */
    public Pagination<PermissionListResponse> getPermissions(QueryParams params) throws MollieException {
        try {
            String uri = "/permissions";

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<Pagination<PermissionListResponse>>() {
                    });
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }
}
