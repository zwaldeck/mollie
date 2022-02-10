package be.woutschoovaerts.mollie.data.subscription;

import be.woutschoovaerts.mollie.data.common.Amount;
import be.woutschoovaerts.mollie.data.common.ApplicationFee;
import be.woutschoovaerts.mollie.data.mandate.MandatePaymentMethod;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionRequest {

    private Amount amount;

    @Builder.Default
    private Optional<Integer> times = Optional.empty();

    private String interval;

    @Builder.Default
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Optional<LocalDate> startDate = Optional.empty();

    private String description;

    private MandatePaymentMethod method;

    @Builder.Default
    private Optional<String> mandateId = Optional.empty();

    @Builder.Default
    private Optional<String> webhookUrl = Optional.empty();

    private Map<String, Object> metadata;

    @Builder.Default
    private Optional<String> profileId = Optional.empty();

    @Builder.Default
    private Optional<ApplicationFee> applicationFee = Optional.empty();
}
