package be.woutschoovaerts.mollie.bugfixes;

import be.woutschoovaerts.mollie.data.common.Amount;
import be.woutschoovaerts.mollie.util.ObjectMapperService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonSerializationFixesTestCase {

    @Test
    void valueOfAmountShouldBeSerializedAsString() throws Exception {
        Amount amountWithString = Amount.builder()
                .currency("EUR")
                .value(new BigDecimal("12.00").setScale(2, RoundingMode.UP))
                .build();

        Amount amountWithDouble = Amount.builder()
                .currency("EUR")
                .value(new BigDecimal(12.00))
                .build();

        String expectedJson = "{\"currency\":\"EUR\",\"value\":\"12.00\"}";

        String result1 = ObjectMapperService.getInstance().getMapper().writeValueAsString(amountWithString);
        String result2 = ObjectMapperService.getInstance().getMapper().writeValueAsString(amountWithDouble);

        assertEquals(expectedJson, result1);
        assertEquals(expectedJson, result2);
    }
}
