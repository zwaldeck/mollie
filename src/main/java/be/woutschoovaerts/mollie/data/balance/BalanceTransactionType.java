package be.woutschoovaerts.mollie.data.balance;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BalanceTransactionType {

    PAYMENT,
    CAPTURE,
    UNAUTHORIZED_DIRECT_DEBIT,
    FAILED_PAYMENT,
    REFUND,
    RETURNED_REFUND,
    CHARGEBACK,
    CHARGEBACK_REVERSAL,
    OUTGOING_TRANSFER,
    CANCELED_OUTGOING_TRANSFER,
    RETURNED_TRANSFER,
    INVOICE_COMPENSATION,
    BALANCE_CORRECTION,
    APPLICATION_FEE,
    SPLIT_PAYMENT,
    PLATFORM_PAYMENT_REFUND,
    PLATFORM_PAYMENT_CHARGEBACK;

    @JsonValue
    public String getJsonValue() {
        return name().toLowerCase().replace("_", "-");
    }
}
