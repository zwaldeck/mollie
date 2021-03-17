package be.woutschoovaerts.mollie.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

class ISO8601DateFormatSerializerTest {

    private ISO8601DateFormatSerializer dateFormatSerializer;
    private JsonGenerator jsonGenerator;
    private SerializerProvider serializerProvider;
    private ArgumentCaptor<String> argumentCaptor;

    @BeforeEach
    void before() {
        this.dateFormatSerializer = new ISO8601DateFormatSerializer();
        this.jsonGenerator = Mockito.mock(JsonGenerator.class);
        this.serializerProvider = Mockito.mock(SerializerProvider.class);
        this.argumentCaptor = ArgumentCaptor.forClass(String.class);
    }

    /**
     * Note: this should not occur according to
     * {@link com.fasterxml.jackson.databind.JsonSerializer#serialize(Object, JsonGenerator, SerializerProvider)}
     * documentation.
     */
    @Test
    void serializeNull() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            this.dateFormatSerializer.serialize(null, jsonGenerator, serializerProvider);
        });
    }

    @Test
    void serializeEmptyOptional() throws IOException {
        // when
        this.dateFormatSerializer.serialize(Optional.empty(), jsonGenerator, serializerProvider);

        // then
        Mockito.verify(jsonGenerator).writeNull();
    }

    @Test
    void serializeDate() throws IOException {
        // given
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.JANUARY, 1, 12, 0);
        Date date = calendar.getTime();

        // when
        this.dateFormatSerializer.serialize(Optional.of(date), jsonGenerator, serializerProvider);

        // then
        Mockito.verify(jsonGenerator).writeString(argumentCaptor.capture());
        Assertions.assertEquals("2022-01-01", argumentCaptor.getValue());
    }

    @Test
    void serializeMinimumDate() throws IOException {
        // given
        Calendar calendar = Calendar.getInstance();
        calendar.set(0, Calendar.JANUARY, 1, 0, 0);
        Date date = calendar.getTime();

        // when
        this.dateFormatSerializer.serialize(Optional.of(date), jsonGenerator, serializerProvider);

        // then
        Mockito.verify(jsonGenerator).writeString(argumentCaptor.capture());
        Assertions.assertEquals("0001-01-01", argumentCaptor.getValue());
    }

}