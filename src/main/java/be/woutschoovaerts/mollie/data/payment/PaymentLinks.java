package be.woutschoovaerts.mollie.data.payment;

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
public class PaymentLinks {

    private Link self;

    private Link checkout;

    private Optional<Link> refunds = Optional.empty();

    private Optional<Link> chargebacks = Optional.empty();

    private Optional<Link> captures = Optional.empty();

    private Optional<Link> settlement = Optional.empty();

    private Link documentation;

    private Optional<Link> mandate = Optional.empty();

    private Optional<Link> subscription = Optional.empty();

    private Optional<Link> customer = Optional.empty();

    private Optional<Link> order = Optional.empty();

    private Optional<Link> status = Optional.empty();

    private Optional<Link> payOnline = Optional.empty();

    private Optional<Link> changePaymentState = Optional.empty();
}
