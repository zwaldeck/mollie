package be.woutschoovaerts.mollie.serializer;

import be.woutschoovaerts.mollie.data.payment.PaymentMethod;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

public class PaymentMethodSerializer extends JsonSerializer<List<PaymentMethod>> {
    @Override
    public void serialize(List<PaymentMethod> paymentMethods,
                          JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        if (paymentMethods.isEmpty()) {
            jsonGenerator.writeNull();
            return;
        }

        if (paymentMethods.size() == 1) {
            jsonGenerator.writeObject(paymentMethods.get(0));
        } else {
            jsonGenerator.writeObject(paymentMethods);
        }
    }
}
