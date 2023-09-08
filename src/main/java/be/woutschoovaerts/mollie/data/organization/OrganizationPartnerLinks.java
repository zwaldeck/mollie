package be.woutschoovaerts.mollie.data.organization;

import be.woutschoovaerts.mollie.data.common.Link;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrganizationPartnerLinks {

    private Link self;

    private Link documentation;

    private Link signuplink;

}
