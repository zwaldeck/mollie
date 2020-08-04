package be.woutschoovaerts.mollie.data.payment;

import be.woutschoovaerts.mollie.data.common.Amount;
import be.woutschoovaerts.mollie.data.common.ApplicationFee;
import be.woutschoovaerts.mollie.data.common.Locale;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
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

    private Date createdAt;

    private String status;

    private boolean isCancelable;

    private Optional<Date> authorizedAt;

    private Optional<Date> paidAt;

    private Optional<Date> canceledAt;

    private Date expiresAt;

    private Optional<Date> expiredAt;

    private Optional<Date> failedAt;

    private Amount amount;

    private Optional<Amount> amountRefunded;

    private Optional<Amount> amountRemaining;

    private Optional<Amount> amountCaptured;

    private String description;

    private String redirectUrl;

    private Optional<String> webhookUrl;

    private PaymentMethod method;

    private Map<String, Object> metadata;

    private Locale locale;

    private Optional<String> countryCode;

    private String profileId;

    private Optional<Amount> settlementAmount;

    private Optional<String> settlementId;

    private Optional<String> customerId;

    private SequenceType sequenceType;

    private Optional<String> mandateId;

    private Optional<String> subscriptionId;

    private Optional<String> orderId;

    private Optional<ApplicationFee> applicationFee;

    @JsonProperty("_links")
    private PaymentLinks links;

    private PaymentDetailsResponse details;

    @JsonProperty("_embedded")
    private PaymentEmbedded embedded;
}
