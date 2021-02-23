package be.woutschoovaerts.mollie.data.invoice;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum InvoiceStatus {

    OPEN,
    PAID,
    OVERDUE;

    @JsonValue
    public String getJsonValue() {
        return name().toLowerCase().replace('_', '-');
    }
}
