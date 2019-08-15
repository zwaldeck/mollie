package be.feelio.mollie.data.payment;

import be.feelio.mollie.data.common.Link;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentLinksResponse {

    private Link self;

    private Link checkout;

    private Optional<Link> refunds;

    private Optional<Link> chargebacks;

    private Optional<Link> captures;

    private Optional<Link> settlement;

    private Link documentation;

    private Optional<Link> mandate;

    private Optional<Link> subscription;

    private Optional<Link> customer;

    private Optional<Link> order;

    private Optional<Link> status;

    private Optional<Link> payOnline;
}
