package be.woutschoovaerts.mollie.data.payment;

import be.woutschoovaerts.mollie.data.common.Amount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRouteResponse {

    private Amount amount;

    private PaymentRouteDestination destination;

    @Builder.Default
    private Optional<OffsetDateTime> releaseDate = Optional.empty();
}
