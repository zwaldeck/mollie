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

public class InvoiceHandler extends AbstractHandler {

    private static final Logger log = LoggerFactory.getLogger(InvoiceHandler.class);

    public InvoiceHandler(String baseApiUrl) {
        super(baseApiUrl, log);
    }

    public Pagination<InvoicesListResponse> getInvoices() throws MollieException {
        return getInvoices(QueryParams.EMPTY);
    }

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

    public InvoiceResponse getInvoice(String id) throws MollieException {
        return getInvoice(id, QueryParams.EMPTY);
    }

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
