package be.woutschoovaerts.mollie.data.payment;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PaymentMethod {

    APPLE_PAY("applepay"),
    BANCONTACT("bancontact"),
    BANK_TRANSFER("banktransfer"),
    BELFIUS("belfius"),
    BILLIE("billie"),
    CREDIT_CARD("creditcard"),
    DIRECT_DEBIT("directdebit"),
    EPS("eps"),
    GIFT_CARD("giftcard"),
    GIRO_PAY("giropay"),
    IDEAL("ideal"),
    IN3("in3"),
    KBC("kbc"),
    KLARNA_PAY_LATER("klarnapaylater"),
    KLARNA_PAY_NOW("klarnapaynow"),
    KLARNA_SLICE_IT("klarnasliceit"),
    MY_BANK("mybank"),
    PAYPAL("paypal"),
    PAY_SAFE_CARD("paysafecard"),
    PRZELEWY24("przelewy24"),
    SOFORT("sofort"),
    TWINT("twint"),
    VOUCHER("voucher");

    private final String value;

    @JsonValue
    public String getJsonValue() {
        return value;
    }

}
