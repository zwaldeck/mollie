package be.woutschoovaerts.mollie.data.subscription;

import be.woutschoovaerts.mollie.data.common.Amount;
import be.woutschoovaerts.mollie.data.common.ApplicationFee;
import be.woutschoovaerts.mollie.data.links.SubscriptionLinks;
import be.woutschoovaerts.mollie.data.mandate.MandatePaymentMethod;
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
public class SubscriptionResponse {

    private String resource;

    private String id;

    private String mode;

    private SubscriptionStatus status;

    private Amount amount;

    private int times;

    private int timesRemaining;

    private String interval;

    private OffsetDateTime startDate;

    @Builder.Default
    private Optional<OffsetDateTime> nextPaymentDate = Optional.empty();

    private String description;

    @Builder.Default
    private Optional<MandatePaymentMethod> method = Optional.empty();

    private ApplicationFee applicationFee;

    private Map<String, Object> metadata;

    private String webhookUrl;

    @Builder.Default
    private Optional<String> customerId = Optional.empty();

    @Builder.Default
    private Optional<String> mandateId = Optional.empty();

    @Builder.Default
    private Optional<String> profileId = Optional.empty();

    private OffsetDateTime createdAt;

    private OffsetDateTime canceledAt;

    @JsonProperty("_links")
    private SubscriptionLinks links;
}
