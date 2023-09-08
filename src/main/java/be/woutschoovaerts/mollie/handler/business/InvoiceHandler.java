package be.woutschoovaerts.mollie.handler.business;

import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.data.invoice.InvoiceResponse;
import be.woutschoovaerts.mollie.data.invoice.InvoicesListResponse;
import be.woutschoovaerts.mollie.exception.MollieException;
import be.woutschoovaerts.mollie.util.QueryParams;
import be.woutschoovaerts.mollie.util.RestService;
import com.fasterxml.jackson.core.type.TypeReference;
import kong.unirest.UnirestException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Handles the Invoices API <a href="https://docs.mollie.com/reference/v2/invoices-api/get-invoice">Mollie docs</a>
 *
 * @author Wout Schoovaerts
 */
@RequiredArgsConstructor
public class InvoiceHandler {

    private static final Logger log = LoggerFactory.getLogger(InvoiceHandler.class);

    private static final TypeReference<Pagination<InvoicesListResponse>> INVOICES_LIST_RESPONSE_TYPE = new TypeReference<>() {};
    private static final TypeReference<InvoiceResponse> INVOICE_RESPONSE_TYPE = new TypeReference<>() {};

    private final RestService restService;

    /**
     * Retrieve all invoices on the account. Optionally filter on year or invoice number..
     *
     * @return Pagination<InvoicesListResponse> object
     * @throws MollieException when something went wrong
     */
    public Pagination<InvoicesListResponse> getInvoices() throws MollieException {
        return getInvoices(new QueryParams());
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

            return restService.get(uri, params, false, INVOICES_LIST_RESPONSE_TYPE);
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
        return getInvoice(id, new QueryParams());
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

            return restService.get(uri, params, false, INVOICE_RESPONSE_TYPE);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }
}
