package be.woutschoovaerts.mollie.data.paymentLink;

import be.woutschoovaerts.mollie.data.common.Link;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentLinkLinks {

    private Link self;

    private Link paymentLink;

    private Link documentation;
}
