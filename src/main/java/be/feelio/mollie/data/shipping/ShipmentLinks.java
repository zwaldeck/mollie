package be.feelio.mollie.data.shipping;

import be.feelio.mollie.data.common.Link;
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
