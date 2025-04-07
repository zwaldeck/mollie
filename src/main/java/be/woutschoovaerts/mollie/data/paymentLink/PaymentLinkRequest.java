package be.woutschoovaerts.mollie.data.paymentLink;

import be.woutschoovaerts.mollie.data.common.Amount;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @Builder.Default
    private Optional<String> redirectUrl = Optional.empty();

    @Builder.Default
    private Optional<String> webhookUrl = Optional.empty();

    @Builder.Default
    private Optional<String> profileId = Optional.empty();

    @Builder.Default
    private Optional<Boolean> reusable = Optional.empty();

    @Builder.Default
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Optional<OffsetDateTime> expiresAt = Optional.empty();

    @Builder.Default
    private Optional<Boolean> testmode = Optional.empty();
}
