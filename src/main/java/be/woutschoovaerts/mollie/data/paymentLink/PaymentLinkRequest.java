package be.woutschoovaerts.mollie.data.paymentLink;

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
public class PaymentLinkRequest {

    private String description;

    private Amount amount;

    private Optional<String> redirectUrl = Optional.empty();

    private Optional<String> webhookUrl = Optional.empty();

    private Optional<OffsetDateTime> expiresAt = Optional.empty();

    // OAuth params
    private Optional<String> profileId = Optional.empty();

    @Builder.Default
    private Optional<Boolean> testmode = Optional.empty();
}
