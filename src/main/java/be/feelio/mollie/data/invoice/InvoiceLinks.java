package be.feelio.mollie.data.invoice;

import be.feelio.mollie.data.common.Link;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceLinks {

    private Link self;

    private Link pdf;

    private Link documentation;
}
