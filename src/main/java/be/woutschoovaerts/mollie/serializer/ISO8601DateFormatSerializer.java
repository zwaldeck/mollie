package be.woutschoovaerts.mollie.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

public class ISO8601DateFormatSerializer extends JsonSerializer<Optional<Date>> {

    private static final SimpleDateFormat ISO_8601_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void serialize(Optional<Date> optionalDate, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider)
            throws IOException {
        Objects.requireNonNull(optionalDate, "'optionalDate' parameter to serialize cannot be null!");

        if (!optionalDate.isPresent()) {
            jsonGenerator.writeNull();
            return;
        }

        Date date = optionalDate.get();
        jsonGenerator.writeString(ISO_8601_DATE_FORMAT.format(date));
    }
}
