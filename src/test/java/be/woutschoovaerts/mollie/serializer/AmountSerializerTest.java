package be.woutschoovaerts.mollie.serializer;

import be.woutschoovaerts.mollie.data.common.Amount;
import com.fasterxml.jackson.core.JsonGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AmountSerializerTest {

    @Mock
    private JsonGenerator jsonGenerator;

    @InjectMocks
    private AmountSerializer serializer;

    @Captor
    private ArgumentCaptor<BigDecimal> bigDecimalCaptor;

    @Test
    void whenUsingNormalCurrency_shouldSerializeWithDecimals() throws Exception {
        serializer.serialize(Amount.builder().currency("EUR").value(new BigDecimal("12.456")).build(), jsonGenerator, null);

        verify(jsonGenerator).writeStringField("currency", "EUR");
        verify(jsonGenerator).writeObjectField(eq("value"), bigDecimalCaptor.capture());

        assertEquals(2, bigDecimalCaptor.getValue().scale());
    }

    @Test
    void whenUsingCurrencyWhereDecimalsAreDisable_shouldNotSerializeWithDecimals() throws Exception {
        serializer.serialize(Amount.builder().currency("JPY").value(new BigDecimal("12.456")).build(), jsonGenerator, null);

        verify(jsonGenerator).writeStringField("currency", "JPY");
        verify(jsonGenerator).writeObjectField(eq("value"), bigDecimalCaptor.capture());

        assertEquals(0, bigDecimalCaptor.getValue().scale());
    }
}