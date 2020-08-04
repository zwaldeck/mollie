package be.woutschoovaerts.mollie.data.settlement;

import be.woutschoovaerts.mollie.data.common.Link;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SettlementLinks {

    private Link self;

    private Link payments;

    private Link refunds;

    private Link chargebacks;

    private Link captures;

    private Link invoice;

    private Link documentation;
}
