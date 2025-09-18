package be.woutschoovaerts.mollie.data.mandate;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MandatePaymentMethod {

    DIRECTDEBIT,
    CREDITCARD,
    PAYPAL,
    BANCONTACT;

    @JsonValue
    public String getJsonValue() {
        return name().toLowerCase().replace('_', '-');
    }
}
