package be.woutschoovaerts.mollie.serializer;

import be.woutschoovaerts.mollie.data.payment.PaymentMethod;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class PaymentMethodSerializer extends JsonSerializer<Optional<List<PaymentMethod>>> {
    @Override
    public void serialize(Optional<List<PaymentMethod>> paymentMethods,
                          JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        if (paymentMethods.isEmpty()) {
            jsonGenerator.writeNull();
            return;
        }

        List<PaymentMethod> methods = paymentMethods.get();

        if (methods.size() == 1) {
            jsonGenerator.writeObject(methods.get(0));
        } else {
            jsonGenerator.writeObject(methods);
        }
    }
}
