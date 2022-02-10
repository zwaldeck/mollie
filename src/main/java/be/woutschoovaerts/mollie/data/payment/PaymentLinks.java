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

    @Builder.Default
    private Optional<Link> refunds = Optional.empty();

    @Builder.Default
    private Optional<Link> chargebacks = Optional.empty();

    @Builder.Default
    private Optional<Link> captures = Optional.empty();

    @Builder.Default
    private Optional<Link> settlement = Optional.empty();

    private Link documentation;

    @Builder.Default
    private Optional<Link> mandate = Optional.empty();

    @Builder.Default
    private Optional<Link> subscription = Optional.empty();

    @Builder.Default
    private Optional<Link> customer = Optional.empty();

    @Builder.Default
    private Optional<Link> order = Optional.empty();

    @Builder.Default
    private Optional<Link> status = Optional.empty();

    @Builder.Default
    private Optional<Link> payOnline = Optional.empty();

    @Builder.Default
    private Optional<Link> changePaymentState = Optional.empty();
}
