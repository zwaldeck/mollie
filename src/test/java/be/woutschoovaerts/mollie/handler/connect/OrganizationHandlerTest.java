package be.woutschoovaerts.mollie.handler.connect;

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
class OrganizationHandlerTest {

    @Mock
    private RestService restService;

    @InjectMocks
    private OrganizationHandler handler;

    @Test
    void getMyOrganization() throws Exception {
        String uri = "/organizations/me";

        handler.getMyOrganization();

        verify(restService).get(eq(uri), any(QueryParams.class), eq(false), any(TypeReference.class));
    }

    @Test
    void getOrganization() throws Exception {
        String uri = "/organizations/organization_id";

        handler.getOrganization("organization_id");

        verify(restService).get(eq(uri), any(QueryParams.class), eq(false), any(TypeReference.class));
    }

    @Test
    void getPartner() throws Exception {
        String uri = "/organizations/me/partner";

        handler.getPartner();

        verify(restService).get(eq(uri), any(QueryParams.class), eq(false), any(TypeReference.class));
    }
}