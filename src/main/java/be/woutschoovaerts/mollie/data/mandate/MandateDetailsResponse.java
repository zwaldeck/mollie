package be.woutschoovaerts.mollie.data.mandate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MandateDetailsResponse {

    @Builder.Default
    private Optional<String> consumerName = Optional.empty();

    @Builder.Default
    private Optional<String> consumerAccount = Optional.empty();

    @Builder.Default
    private Optional<String> consumerBic = Optional.empty();

    @Builder.Default
    private Optional<String> cardHolder = Optional.empty();

    @Builder.Default
    private Optional<String> cardNumber = Optional.empty();

    @Builder.Default
    private Optional<String> cardLabel = Optional.empty();

    @Builder.Default
    private Optional<String> cardFingerprint = Optional.empty();

    @Builder.Default
    private Optional<LocalDate> cardExpiryDate = Optional.empty();

}
