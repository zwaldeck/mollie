package be.feelio.mollie.data.links;

import be.feelio.mollie.data.common.Link;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MandateLinks {

    private Link self;

    private Link customer;

    private Link documentation;

}
