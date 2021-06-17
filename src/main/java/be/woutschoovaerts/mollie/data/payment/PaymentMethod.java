package be.woutschoovaerts.mollie.data.payment;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PaymentMethod {

    APPLE_PAY("applepay"),
    BANCONTACT("bancontact"),
    BANK_TRANSFER("banktransfer"),
    BELFIUS("belfius"),
    CREDIT_CARD("creditcard"),
    DIRECT_DEBIT("directdebit"),
    EPS("eps"),
    GIFT_CARD("giftcard"),
    GIRO_PAY("giropay"),
    IDEAL("ideal"),
    ING_HOME_PAY("inghomepay"),
    KBC("kbc"),
    KLARNA_PAY_LAYER("klarnapaylater"),
    KARNASLICEIT("klarnasliceit"),
    MY_BANK("mybank"),
    PAYPAL("paypal"),
    PAY_SAFE_CARD("paysafecard"),
    PRZELEWY24("przelewy24"),
    SOFORT("sofort"),
    VOUCHER("voucher");

    private final String value;

    @JsonValue
    public String getJsonValue() {
        return value;
    }

}
