package be.woutschoovaerts.mollie.data.payment;

import be.woutschoovaerts.mollie.data.common.AddressResponse;
import be.woutschoovaerts.mollie.data.common.Amount;
import be.woutschoovaerts.mollie.data.common.ApplicationFee;
import be.woutschoovaerts.mollie.data.common.Locale;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;
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

    private String description;

    private Amount amount;

    @Builder.Default
    private Optional<Amount> amountRefunded = Optional.empty();

    @Builder.Default
    private Optional<Amount> amountRemaining = Optional.empty();

    @Builder.Default
    private Optional<Amount> amountCaptured = Optional.empty();

    @Builder.Default
    private Optional<Amount> amountChargedBack = Optional.empty();

    @Builder.Default
    private Optional<Amount> settlementAmount = Optional.empty();

    @Builder.Default
    private Optional<String> redirectUrl = Optional.empty();

    @Builder.Default
    private Optional<String> cancelUrl = Optional.empty();

    @Builder.Default
    private Optional<String> webhookUrl = Optional.empty();

    @Builder.Default
    private Optional<List<PaymentLineResponse>> lines = Optional.empty();

    @Builder.Default
    private Optional<AddressResponse> billingAddress = Optional.empty();

    @Builder.Default
    private Optional<AddressResponse> shippingAddress = Optional.empty();

    private Locale locale;

    @Builder.Default
    private Optional<String> countryCode = Optional.empty();

    private PaymentMethod method;

    @Builder.Default
    private Optional<String> issuer = Optional.empty();

    @Builder.Default
    private Optional<String> restrictPaymentMethodsToCountry = Optional.empty();

    private Map<String, Object> metadata;

    @Builder.Default
    private Optional<String> captureMode = Optional.empty();

    @Builder.Default
    private Optional<String> captureDelay = Optional.empty();

    @Builder.Default
    private Optional<String> captureBefore = Optional.empty();

    @Builder.Default
    private Optional<ApplicationFee> applicationFee = Optional.empty();

    @Builder.Default
    private Optional<List<PaymentRouteResponse>> routing = Optional.empty();

    private SequenceType sequenceType;

    @Builder.Default
    private Optional<String> subscriptionId = Optional.empty();

    @Builder.Default
    private Optional<String> mandateId = Optional.empty();

    @Builder.Default
    private Optional<String> customerId = Optional.empty();

    @Builder.Default
    private Optional<String> profileId = Optional.empty();

    @Builder.Default
    private Optional<String> settlementId = Optional.empty();

    @Builder.Default
    private Optional<String> orderId = Optional.empty();

    private PaymentStatus status;

    @Builder.Default
    private Optional<PaymentStatusReasonResponse> statusReason = Optional.empty();

    private boolean isCancelable;

    private PaymentDetailsResponse details;

    private OffsetDateTime createdAt;

    @Builder.Default
    private Optional<OffsetDateTime> authorizedAt = Optional.empty();

    @Builder.Default
    private Optional<OffsetDateTime> paidAt = Optional.empty();

    @Builder.Default
    private Optional<OffsetDateTime> canceledAt = Optional.empty();

    @Builder.Default
    private Optional<OffsetDateTime> expiresAt = Optional.empty();

    @Builder.Default
    private Optional<OffsetDateTime> expiredAt = Optional.empty();

    @Builder.Default
    private Optional<OffsetDateTime> failedAt = Optional.empty();

    @JsonProperty("_links")
    private PaymentLinks links;

    @JsonProperty("_embedded")
    private PaymentEmbedded embedded;

    // An extra setter so we can serialize 'isCancelable' (https://github.com/zwaldeck/mollie/issues/45)
    public void setIsCancelable(boolean cancelable) {
        this.isCancelable = cancelable;
    }
}
