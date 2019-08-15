package be.feelio.mollie.data.subscription;

import be.feelio.mollie.data.common.Amount;
import be.feelio.mollie.data.common.ApplicationFee;
import be.feelio.mollie.data.mandate.MandatePaymentMethod;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class SubscriptionRequest {

    private Amount amount;

    private Optional<Integer> times;

    private String interval;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Optional<Date> startDate;

    private String description;

    private MandatePaymentMethod method;

    private Optional<String> mandateId;

    private Optional<String> webhookUrl;

    private Map<String, Object> metadata;

    private Optional<String> profileId;

    private Optional<ApplicationFee> applicationFee;
}
