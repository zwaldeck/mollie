package be.woutschoovaerts.mollie.handler.client;

import be.woutschoovaerts.mollie.data.client.link.ClientLinkRequest;
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
class ClientLinkHandlerTest {

    @Mock
    private RestService restService;

    @InjectMocks
    private ClientLinkHandler handler;

    @Test
    void createClientLink() throws Exception {
        ClientLinkRequest request = ClientLinkRequest.builder()
                .name("My client link")
                .build();
        String uri = "/client-links";

        handler.createClientLink(request);

        verify(restService).post(eq(uri), eq(request), any(QueryParams.class), any(TypeReference.class));
    }
}