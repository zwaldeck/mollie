package be.feelio.mollie.handler;

import be.feelio.mollie.data.common.Pagination;
import be.feelio.mollie.data.enums.Permission;
import be.feelio.mollie.data.response.PermissionListResponse;
import be.feelio.mollie.data.response.PermissionResponse;
import be.feelio.mollie.exception.MollieException;
import be.feelio.mollie.util.ObjectMapperService;
import be.feelio.mollie.util.QueryParams;
import com.fasterxml.jackson.core.type.TypeReference;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class PermissionHandler extends AbstractHandler {

    private static final Logger log = LoggerFactory.getLogger(PermissionHandler.class);

    public PermissionHandler(String baseUrl) {
        super(baseUrl, log);
    }

    public PermissionResponse getPermission(Permission permission) throws MollieException {
        return getPermission(permission, QueryParams.EMPTY);
    }

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

    public Pagination<PermissionListResponse> getPermissions() throws MollieException {
        return getPermissions(QueryParams.EMPTY);
    }

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
