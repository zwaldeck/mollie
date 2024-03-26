package be.woutschoovaerts.mollie.handler.payments;

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
class TerminalHandlerTest {

    @Mock
    private RestService restService;

    @InjectMocks
    private TerminalHandler handler;

    @Test
    void listTerminals() throws Exception{

        String uri = "/terminals";

        handler.listTerminals();

        verify(restService).get(eq(uri), any(TypeReference.class));
    }

    @Test
    void getTerminal() throws Exception {
        String uri = "/terminals/terminal_id";

        handler.getTerminal("terminal_id");

        verify(restService).get(eq(uri), any(TypeReference.class));
    }
}