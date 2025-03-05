package be.woutschoovaerts.mollie.serializer;

import be.woutschoovaerts.mollie.data.common.Amount;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class AmountSerializer extends JsonSerializer<Amount> {

    private static final List<String> CURRENCIES_DECIMALS_DISABLED = List.of("JPY");

    @Override
    public void serialize(Amount amount, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("currency", amount.getCurrency());

        jsonGenerator.writeObjectField("value", sanitizeValue(amount));
        jsonGenerator.writeEndObject();
    }

    private BigDecimal sanitizeValue(Amount amount) {
        int scale = CURRENCIES_DECIMALS_DISABLED.contains(amount.getCurrency().toUpperCase()) ? 0 : 2;
        return amount.getValue().setScale(scale, RoundingMode.HALF_UP);
    }
}
