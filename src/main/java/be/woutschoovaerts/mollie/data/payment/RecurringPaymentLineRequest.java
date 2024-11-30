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
public class RecurringPaymentLineRequest {

    @Builder.Default
    private Optional<String> description = Optional.empty();

    private String interval;

    @Builder.Default
    private Optional<Amount> amount = Optional.empty();

    @Builder.Default
    private Optional<Integer> times = Optional.empty();

    @Builder.Default
    private Optional<OffsetDateTime> startDate = Optional.empty();
}
