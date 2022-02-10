package be.woutschoovaerts.mollie.data.payment;

import be.woutschoovaerts.mollie.data.common.Amount;
import be.woutschoovaerts.mollie.data.common.ApplicationFee;
import be.woutschoovaerts.mollie.data.common.Locale;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {

    private String resource;

    private String id;

    private String mode;

    private OffsetDateTime createdAt;

    private PaymentStatus status;

    private Boolean isCancelable;

    @Builder.Default
    private Optional<OffsetDateTime> authorizedAt = Optional.empty();

    @Builder.Default
    private Optional<OffsetDateTime> paidAt = Optional.empty();

    @Builder.Default
    private Optional<OffsetDateTime> canceledAt = Optional.empty();

    private OffsetDateTime expiresAt;

    @Builder.Default
    private Optional<OffsetDateTime> expiredAt = Optional.empty();

    @Builder.Default
    private Optional<OffsetDateTime> failedAt = Optional.empty();

    private Amount amount;

    @Builder.Default
    private Optional<Amount> amountRefunded = Optional.empty();

    @Builder.Default
    private Optional<Amount> amountRemaining = Optional.empty();

    @Builder.Default
    private Optional<Amount> amountCaptured = Optional.empty();

    private String description;

    private String redirectUrl;

    @Builder.Default
    private Optional<String> webhookUrl = Optional.empty();

    private PaymentMethod method;

    private Map<String, Object> metadata;

    private Locale locale;

    @Builder.Default
    private Optional<String> countryCode = Optional.empty();

    private String profileId;

    @Builder.Default
    private Optional<Amount> settlementAmount = Optional.empty();

    @Builder.Default
    private Optional<String> settlementId = Optional.empty();

    @Builder.Default
    private Optional<String> customerId = Optional.empty();

    private SequenceType sequenceType;

    @Builder.Default
    private Optional<String> mandateId = Optional.empty();

    @Builder.Default
    private Optional<String> subscriptionId = Optional.empty();

    @Builder.Default
    private Optional<String> orderId = Optional.empty();

    @Builder.Default
    private Optional<ApplicationFee> applicationFee = Optional.empty();

    @JsonProperty("_links")
    private PaymentLinks links;

    private PaymentDetailsResponse details;

    @JsonProperty("_embedded")
    private PaymentEmbedded embedded;
}
