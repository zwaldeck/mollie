package be.feelio.mollie.handler;

import be.feelio.mollie.data.common.Pagination;
import be.feelio.mollie.data.invoice.InvoiceResponse;
import be.feelio.mollie.data.invoice.InvoicesListResponse;
import be.feelio.mollie.exception.MollieException;
import be.feelio.mollie.util.ObjectMapperService;
import be.feelio.mollie.util.QueryParams;
import com.fasterxml.jackson.core.type.TypeReference;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Handles the Invoices API <a href="https://docs.mollie.com/reference/v2/invoices-api/get-invoice">Mollie docs</a>
 *
 * @author Wout Schoovaerts
 */
public class InvoiceHandler extends AbstractHandler {

    private static final Logger log = LoggerFactory.getLogger(InvoiceHandler.class);

    public InvoiceHandler(String baseApiUrl) {
        super(baseApiUrl, log);
    }

    /**
     * Retrieve all invoices on the account. Optionally filter on year or invoice number..
     *
     * @return Pagination<InvoicesListResponse> object
     * @throws MollieException when something went wrong
     */
    public Pagination<InvoicesListResponse> getInvoices() throws MollieException {
        return getInvoices(QueryParams.EMPTY);
    }

    /**
     * Retrieve all invoices on the account. Optionally filter on year or invoice number.
     *
     * @param params A map of query params
     * @return Pagination<InvoicesListResponse> object
     * @throws MollieException when something went wrong
     */
    public Pagination<InvoicesListResponse> getInvoices(QueryParams params) throws MollieException {
        try {
            String uri = "/invoices";

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<Pagination<InvoicesListResponse>>() {
                    });
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Retrieve details of an invoice, using the invoice’s identifier.
     * <p>
     * If you want to retrieve the details of an invoice by its invoice number, use the list endpoint with the reference parameter.
     *
     * @param id     An invoice ID
     * @return Pagination<InvoicesListResponse> object
     * @throws MollieException when something went wrong
     */
    public InvoiceResponse getInvoice(String id) throws MollieException {
        return getInvoice(id, QueryParams.EMPTY);
    }

    /**
     * Retrieve details of an invoice, using the invoice’s identifier.
     * <p>
     * If you want to retrieve the details of an invoice by its invoice number, use the list endpoint with the reference parameter.
     *
     * @param id     An invoice ID
     * @param params A map of query params
     * @return Pagination<InvoicesListResponse> object
     * @throws MollieException when something went wrong
     */
    public InvoiceResponse getInvoice(String id, QueryParams params) throws MollieException {
        try {
            String uri = "/invoices/" + id;

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), InvoiceResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }
}
