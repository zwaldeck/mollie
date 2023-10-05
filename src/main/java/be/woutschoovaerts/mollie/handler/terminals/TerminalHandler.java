package be.woutschoovaerts.mollie.handler.terminals;

import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.data.terminals.Terminal;
import be.woutschoovaerts.mollie.data.terminals.TerminalListResponse;
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
 * Handles the Terminal API <a href="https://docs.mollie.com/reference/v2/terminals-api/overview">Mollie docs</a>
 *
 * @author Marteijn Nouwens
 */
@RequiredArgsConstructor
public class TerminalHandler {

    private static final Logger log = LoggerFactory.getLogger(TerminalHandler.class);

    private static final TypeReference<Pagination<TerminalListResponse>> TERMINALS_LIST_RESPONSE_TYPE = new TypeReference<>() {
    };

    private static final TypeReference<Terminal> TERMINAL_RESPONSE_TYPE = new TypeReference<>() {
    };

    private final RestService restService;

    /**
     * Retrieve all Terminals.
     * The results are not paginated.
     *
     * @return List response of TerminalListResponse objects
     * @throws MollieException when something went wrong
     */
    public Pagination<TerminalListResponse> listTerminals() throws MollieException {
        try {
            String uri = "/terminals";
            return restService.get(uri, TERMINALS_LIST_RESPONSE_TYPE);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }


    /**
     * Retrieve the details of the Terminal
     * @param id The terminal id
     * @throws MollieException when something went wrong
     */
    public Terminal terminalDetails(String id) throws MollieException{
        try {
            String uri = "/terminals/" + id;
            return restService.get(uri, new QueryParams(), true, TERMINAL_RESPONSE_TYPE);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }
}
