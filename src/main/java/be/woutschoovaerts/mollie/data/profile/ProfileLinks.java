package be.woutschoovaerts.mollie.data.profile;

import be.woutschoovaerts.mollie.data.common.Link;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileLinks {

    private Link self;

    private Link chargebacks;

    private Link methods;

    private Link payments;

    private Link refunds;

    private Link checkoutPreviewUrl;

    private Link documentation;
}
