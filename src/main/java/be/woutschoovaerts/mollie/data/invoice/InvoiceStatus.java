package be.woutschoovaerts.mollie.data.invoice;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum InvoiceStatus {

    OPEN("open"),
    PAID("paid"),
    OVERDUE("overdue");

    private final String jsonValue;

    @JsonValue
    public String getJsonValue() {
        return jsonValue;
    }
}
