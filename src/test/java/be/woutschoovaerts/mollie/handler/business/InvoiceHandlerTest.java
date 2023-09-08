package be.woutschoovaerts.mollie.handler.business;

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
class InvoiceHandlerTest {

    @Mock
    private RestService restService;

    @InjectMocks
    private InvoiceHandler handler;

    @Test
    void getInvoices() throws Exception {
        String uri = "/invoices";

        handler.getInvoices();

        verify(restService).get(eq(uri), any(QueryParams.class), eq(false), any(TypeReference.class));
    }

    @Test
    void getInvoice() throws Exception {
        String uri = "/invoices/invoice_id";

        handler.getInvoice("invoice_id");

        verify(restService).get(eq(uri), any(QueryParams.class), eq(false), any(TypeReference.class));
    }
}