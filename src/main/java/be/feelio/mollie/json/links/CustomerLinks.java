package be.feelio.mollie.json.links;

import be.feelio.mollie.json.common.Link;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerLinks {

    private Link self;

    private Link mandates;

    private Link subscriptions;

    private Link payments;

    private Link documentation;

}
