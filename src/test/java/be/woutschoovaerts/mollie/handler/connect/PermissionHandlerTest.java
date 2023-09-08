package be.woutschoovaerts.mollie.handler.connect;

import be.woutschoovaerts.mollie.data.permission.Permission;
import be.woutschoovaerts.mollie.util.QueryParams;
import be.woutschoovaerts.mollie.util.RestService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PermissionHandlerTest {

    @Mock
    private RestService restService;

    @InjectMocks
    private PermissionHandler handler;

    @Test
    void getPermission() throws Exception {
        String uri = "/permissions/payments.read";

        handler.getPermission(Permission.PAYMENTS_READ);

        verify(restService).get(eq(uri), any(QueryParams.class), eq(false), any(TypeReference.class));
    }

    @Test
    void getPermissions() throws Exception {
        String uri = "/permissions";

        handler.getPermissions();

        verify(restService).get(eq(uri), any(QueryParams.class), eq(false), any(TypeReference.class));
    }

}