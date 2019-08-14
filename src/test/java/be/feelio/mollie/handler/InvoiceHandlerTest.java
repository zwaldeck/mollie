package be.feelio.mollie.handler;

import be.feelio.mollie.Client;
import be.feelio.mollie.ClientBuilder;
import be.feelio.mollie.data.common.Pagination;
import be.feelio.mollie.data.response.InvoiceResponse;
import be.feelio.mollie.data.response.InvoicesListResponse;
import be.feelio.mollie.exception.MollieException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static be.feelio.mollie.IntegrationTestConstants.API_KEY;
import static be.feelio.mollie.IntegrationTestConstants.ORGANISATION_TOKEN;
import static org.junit.jupiter.api.Assertions.*;

class InvoiceHandlerTest {

    private Client client;

    @BeforeEach
    void setup() {
        client = new ClientBuilder()
                .withApiKey(API_KEY)
                .withOrganizationToken(ORGANISATION_TOKEN)
                .build();
    }

    @Test
    void getInvoices() throws MollieException {
        Pagination<InvoicesListResponse> invoices = client.invoices().getInvoices();

        assertNotNull(invoices);
    }

    @Test
    void getInvoice() throws MollieException {
        Pagination<InvoicesListResponse> invoices = client.invoices().getInvoices();

        assertNotNull(invoices);

        InvoiceResponse response = client.invoices().getInvoice(invoices.getEmbedded().getInvoices().get(0).getId());

        assertNotNull(response);
        assertEquals("invoice", response.getResource());
    }
}