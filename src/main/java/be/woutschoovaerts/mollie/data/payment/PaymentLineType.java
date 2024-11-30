package be.woutschoovaerts.mollie.data.payment;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PaymentLineType {

    PHYSICAL,
    DIGITAL,
    SHIPPING_FEE,
    DISCOUNT,
    STORE_CREDIT,
    GIFT_CARD,
    SURCHARGE,
    TIP;

    @JsonValue
    public String getJsonValue() {
        return name().toLowerCase();
    }
}
