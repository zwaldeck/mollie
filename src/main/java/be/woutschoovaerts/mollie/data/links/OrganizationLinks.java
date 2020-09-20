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

    private Optional<Link> chargebacks = Optional.empty();

    private Optional<Link> customers = Optional.empty();

    private Optional<Link> invoices = Optional.empty();

    private Optional<Link> payments = Optional.empty();

    private Optional<Link> profiles = Optional.empty();

    private Optional<Link> refunds = Optional.empty();

    private Optional<Link> settlements = Optional.empty();

    private Link documentation;

}
