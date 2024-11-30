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

    @Builder.Default
    private Optional<Link> chargebacks = Optional.empty();

    @Builder.Default
    private Optional<Link> customers = Optional.empty();

    @Builder.Default
    private Optional<Link> invoices = Optional.empty();

    @Builder.Default
    private Optional<Link> payments = Optional.empty();

    @Builder.Default
    private Optional<Link> profiles = Optional.empty();

    @Builder.Default
    private Optional<Link> refunds = Optional.empty();

    @Builder.Default
    private Optional<Link> settlements = Optional.empty();

    private Link documentation;

}
