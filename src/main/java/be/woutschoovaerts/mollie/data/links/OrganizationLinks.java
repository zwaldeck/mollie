package be.woutschoovaerts.mollie.data.links;

import be.woutschoovaerts.mollie.data.common.Link;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrganizationLinks {

    private Link self;

    private Optional<Link> chargebacks;

    private Optional<Link> customers;

    private Optional<Link> invoices;

    private Optional<Link> payments;

    private Optional<Link> profiles;

    private Optional<Link> refunds;

    private Optional<Link> settlements;

    private Link documentation;

}
