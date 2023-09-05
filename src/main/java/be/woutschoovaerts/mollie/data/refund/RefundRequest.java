package be.woutschoovaerts.mollie.data.refund;

import be.woutschoovaerts.mollie.data.common.Amount;
import be.woutschoovaerts.mollie.data.common.RoutingReversalRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefundRequest {

    private Amount amount;

    @Builder.Default
    private Optional<String> description = Optional.empty();

    private HashMap<String, Object> metadata;

    @Builder.Default
    private Optional<Boolean> testmode = Optional.empty();

    @Builder.Default
    private Optional<Boolean> reverseRouting = Optional.empty();

    @Builder.Default
    private Optional<List<RoutingReversalRequest>> routingReversals = Optional.empty();
}
