package be.feelio.mollie.json.response;

import be.feelio.mollie.json.common.Amount;
import be.feelio.mollie.json.common.ApplicationFee;
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

    private Optional<Date> paidAt;

    private Optional<Date> canceledAt;

    private Date expiresAt;

    private Optional<Date> expiredAt;

    private Optional<Date> failedAt;

    private Amount amount;

    private Optional<Amount> amountRefunded;

    private Optional<Amount> amountRemaining;

    private String description;

    private String redirectUrl;

    private Optional<String> webhookUrl;

    private String method;

    private Map<String, Object> metadata;

    private String locale;

    private Optional<String> countryCode;

    private String profileId;

    private Optional<Amount> settlementAmount;

    private Optional<String> settlementId;

    private Optional<String> customerId;

    private String sequenceType;

    private Optional<String> mandateId;

    private Optional<String> subscriptionId;

    private Optional<String> orderId;

    private Optional<ApplicationFee> applicationFee;

    @JsonProperty("_links")
    private PaymentLinksResponse links;

    private PaymentDetailsResponse details;
}
