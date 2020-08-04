package be.woutschoovaerts.mollie.data.invoice;

import be.woutschoovaerts.mollie.data.common.Amount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceLine {

    private String period;

    private String description;

    private int count;

    private double vatPercentage;

    private Amount amount;
}
