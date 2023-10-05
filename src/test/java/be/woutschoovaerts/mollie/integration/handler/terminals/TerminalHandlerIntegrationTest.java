package be.woutschoovaerts.mollie.integration.handler.terminals;

import be.woutschoovaerts.mollie.Client;
import be.woutschoovaerts.mollie.ClientBuilder;
import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.data.terminals.Terminal;
import be.woutschoovaerts.mollie.data.terminals.TerminalListResponse;
import be.woutschoovaerts.mollie.exception.MollieException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static be.woutschoovaerts.mollie.IntegrationTestConstants.API_KEY;
import static org.junit.jupiter.api.Assertions.*;

public class TerminalHandlerIntegrationTest {

    private static final String TERMINAL_API_KEY = "test_2z2NMNRbzMQgdKrPkrxnxf4SuqxrrR";
    private Client client;

    @BeforeEach
    void setup() {
        client = new ClientBuilder().withApiKey(TERMINAL_API_KEY).build();
    }

    @Test
    void listTerminals() throws MollieException {
        Pagination<TerminalListResponse> response = client.terminals().listTerminals();
        assertNotNull(response);

        List<Terminal> terminals = response.getEmbedded().getTerminals();
        assertEquals(1, terminals.size());
        Terminal testTerminal = terminals.get(0);
        assertEquals("PAX", testTerminal.getBrand());
        assertEquals("EUR", testTerminal.getCurrency());
        assertEquals("A920", testTerminal.getModel());
        assertNull(testTerminal.getSerialNumber());
        assertEquals("active", testTerminal.getStatus());
        assertEquals("Test terminal", testTerminal.getDescription());
        assertNotNull( testTerminal.getCreatedAt());
        assertNotNull(testTerminal.getUpdatedAt());

        Terminal details = client.terminals().terminalDetails(testTerminal.getId());
        assertEquals("PAX", details.getBrand());
        assertEquals("EUR", details.getCurrency());
        assertEquals("A920", details.getModel());
        assertNull(details.getSerialNumber());
        assertEquals("active", details.getStatus());
        assertEquals("Test terminal", details.getDescription());
        assertNotNull( details.getCreatedAt());
        assertNotNull(details.getUpdatedAt());
    }


}
