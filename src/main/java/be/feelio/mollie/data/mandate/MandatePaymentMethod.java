package be.feelio.mollie.data.mandate;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MandatePaymentMethod {

    DIRECT_DEBIT("directdebit"),
    CREDIT_CARD("creditcard");

    private final String jsonValue;

    @JsonValue
    public String getJsonValue() {
        return jsonValue;
    }
}
