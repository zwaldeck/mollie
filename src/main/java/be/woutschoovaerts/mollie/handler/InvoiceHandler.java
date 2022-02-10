package be.woutschoovaerts.mollie.handler;

import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.data.invoice.InvoiceResponse;
import be.woutschoovaerts.mollie.data.invoice.InvoicesListResponse;
import be.woutschoovaerts.mollie.exception.MollieException;
import be.woutschoovaerts.mollie.util.Config;
import be.woutschoovaerts.mollie.util.QueryParams;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles the Invoices API <a href="https://docs.mollie.com/reference/v2/invoices-api/get-invoice">Mollie docs</a>
 *
 * @author Wout Schoovaerts
 */
public class InvoiceHandler extends AbstractHandler {

    private static final Logger log = LoggerFactory.getLogger(InvoiceHandler.class);

    public InvoiceHandler(String baseApiUrl, Config config) {
        super(baseApiUrl, log, config);
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
        String uri = "/invoices";

        return get(uri, params, new TypeReference<>() {
        });
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
        String uri = "/invoices/" + id;

        return get(uri, params, new TypeReference<>() {
        });
    }
}
