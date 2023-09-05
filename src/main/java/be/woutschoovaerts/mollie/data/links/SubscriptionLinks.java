package be.woutschoovaerts.mollie.data.links;

import be.woutschoovaerts.mollie.data.common.Link;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionLinks {

    private Link self;

    private Link customer;

    private Link profile;

    private Link payments;

    private Link documentation;

}
