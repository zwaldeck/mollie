package be.woutschoovaerts.mollie.data.paymentLink;

import be.woutschoovaerts.mollie.data.common.Amount;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class PaymentLinkResponse {

    private String resource;

    private String id;

    private String mode;

    private String description;

    @Builder.Default
    private Optional<Amount> amount = Optional.empty();

    private boolean archived;

    @Builder.Default
    private Optional<String> redirectUrl = Optional.empty();

    @Builder.Default
    private Optional<String> webhookUrl = Optional.empty();

    @Builder.Default
    private Optional<String> profileId = Optional.empty();

    @Builder.Default
    private Optional<Boolean> reusable = Optional.empty();

    private OffsetDateTime createdAt;

    @Builder.Default
    private Optional<OffsetDateTime> paidAt = Optional.empty();

    @Builder.Default
    private Optional<OffsetDateTime> expiresAt = Optional.empty();

    @JsonProperty("_links")
    private PaymentLinkLinks links;
}
