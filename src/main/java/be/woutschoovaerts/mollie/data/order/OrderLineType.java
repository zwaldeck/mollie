package be.woutschoovaerts.mollie.data.order;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderLineType {

    PHYSICAL,
    DISCOUNT,
    DIGITAL,
    SHIPPING_FEE,
    STORE_CREDIT,
    GIFT_CARD,
    SURCHARGE;

    @JsonValue
    public String getJsonValue() {
        return this.name().toLowerCase();
    }
}
