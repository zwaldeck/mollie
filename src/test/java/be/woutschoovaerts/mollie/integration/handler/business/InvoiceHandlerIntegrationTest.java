package be.woutschoovaerts.mollie.integration.handler.business;

import be.woutschoovaerts.mollie.Client;
import be.woutschoovaerts.mollie.ClientBuilder;
import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.data.invoice.InvoiceResponse;
import be.woutschoovaerts.mollie.data.invoice.InvoicesListResponse;
import be.woutschoovaerts.mollie.exception.MollieException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static be.woutschoovaerts.mollie.IntegrationTestConstants.API_KEY;
import static be.woutschoovaerts.mollie.IntegrationTestConstants.ORGANISATION_TOKEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InvoiceHandlerIntegrationTest {

    private Client client;

    @BeforeEach
    void setup() {
        client = new ClientBuilder()
                .withApiKey(API_KEY)
                .withOrganizationToken(ORGANISATION_TOKEN)
                .build();
    }

    @Test
    @Disabled        // This test works if you fill in an organisation token and remove the @Disabled
    void getInvoices() throws MollieException {
        Pagination<InvoicesListResponse> invoices = client.invoices().getInvoices();

        assertNotNull(invoices);
    }

    @Test
    @Disabled        // This test works if you fill in an organisation token and remove the @Disabled
    void getInvoice() throws MollieException {
        Pagination<InvoicesListResponse> invoices = client.invoices().getInvoices();

        assertNotNull(invoices);

        InvoiceResponse response = client.invoices().getInvoice(invoices.getEmbedded().getInvoices().get(0).getId());

        assertNotNull(response);
        assertEquals("invoice", response.getResource());
    }
}