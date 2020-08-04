package be.woutschoovaerts.mollie.data.shipment;

import be.woutschoovaerts.mollie.data.common.Link;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipmentLinks {

    private Link self;

    private Link order;

    private Link documentation;
}
