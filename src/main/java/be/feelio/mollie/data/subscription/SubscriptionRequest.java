package be.feelio.mollie.data.subscription;

import be.feelio.mollie.data.common.Amount;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionRequest {

    private Amount amount;

    private OptionalInt times;

    private String interval;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Optional<Date> startDate;

    private String description;

    private Optional<String> method;

    private Optional<String> webhookUrl;

    private Map<String, Object> metadata;
}
