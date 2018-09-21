package be.feelio.mollie.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueryParamsTest {

    @Test
    void toString_success() {
        QueryParams params = new QueryParams();
        params.put("param1", "value1");
        params.put("param2", "value2");
        params.put("param3", "value3");

        assertEquals("?param1=value1&param2=value2&param3=value3", params.toString());
    }

    @Test
    void toString_empty() {
        assertEquals("", QueryParams.EMPTY.toString());
    }
}