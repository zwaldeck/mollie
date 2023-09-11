package be.woutschoovaerts.mollie.handler.connect;

import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.data.permission.Permission;
import be.woutschoovaerts.mollie.data.permission.PermissionListResponse;
import be.woutschoovaerts.mollie.data.permission.PermissionResponse;
import be.woutschoovaerts.mollie.exception.MollieException;
import be.woutschoovaerts.mollie.util.QueryParams;
import be.woutschoovaerts.mollie.util.RestService;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;

/**
 * Handles the Permissions API <a href="https://docs.mollie.com/reference/v2/permissions-api/get-permission">Mollie docs</a>
 *
 * @author Wout Schoovaerts
 */
@RequiredArgsConstructor
public class PermissionHandler {

    private static final TypeReference<PermissionResponse> PERMISSION_RESPONSE_TYPE = new TypeReference<>() {
    };
    private static final TypeReference<Pagination<PermissionListResponse>> PERMISSIONS_LIST_RESPONSE_TYPE = new TypeReference<>() {
    };

    private final RestService restService;

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
            String uri = "/permissions/" + permission.getValue();

            return restService.get(uri, params, false, PERMISSION_RESPONSE_TYPE);
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
            String uri = "/permissions";

            return restService.get(uri, params, false, PERMISSIONS_LIST_RESPONSE_TYPE);
    }
}
