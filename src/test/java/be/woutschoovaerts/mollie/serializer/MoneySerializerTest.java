package be.woutschoovaerts.mollie.serializer;

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
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MoneySerializerTest {

    @Mock
    private JsonGenerator jsonGenerator;

    @InjectMocks
    private MoneySerializer serializer;

    @Captor
    private ArgumentCaptor<BigDecimal> bigDecimalCaptor;

    @Test
    void serialize() throws Exception {
        serializer.serialize(new BigDecimal("12.456"), jsonGenerator, null);

        verify(jsonGenerator).writeObject(bigDecimalCaptor.capture());
        assertEquals(2, bigDecimalCaptor.getValue().scale());
    }
}