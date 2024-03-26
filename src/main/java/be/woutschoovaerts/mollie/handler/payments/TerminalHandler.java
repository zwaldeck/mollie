package be.woutschoovaerts.mollie.handler.payments;

import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.data.payment.TerminalListResponse;
import be.woutschoovaerts.mollie.data.payment.TerminalResponse;
import be.woutschoovaerts.mollie.exception.MollieException;
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
 * @author Wout Schoovaerts
 */
// TODO: Integration test once it's out of BETA
@RequiredArgsConstructor
public class TerminalHandler {
    private static final Logger log = LoggerFactory.getLogger(TerminalHandler.class);

    private static final TypeReference<Pagination<TerminalListResponse>> TERMINALS_LIST_RESPONSE_TYPE = new TypeReference<>() {
    };

    private static final TypeReference<TerminalResponse> TERMINAL_RESPONSE_TYPE = new TypeReference<>() {
    };

    private final RestService restService;

    /**
     * With this endpoint you can retrieve a list of terminals symbolizing the physical devices that you have received from us. In the list you can find all the terminals linked to your organization or profile, ordered from newest to oldest.
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
     * With this endpoint you can retrieve a single terminal object by its terminal ID. This terminal object symbolizes the physical device that you have received from us. This endpoint is not publicly available yet — please reach out to your account manager to sign up for early access.
     *
     * @param id The terminal id
     * @throws MollieException when something went wrong
     */
    public TerminalResponse getTerminal(String id) throws MollieException {
        try {
            String uri = "/terminals/" + id;
            return restService.get(uri, TERMINAL_RESPONSE_TYPE);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }
}
