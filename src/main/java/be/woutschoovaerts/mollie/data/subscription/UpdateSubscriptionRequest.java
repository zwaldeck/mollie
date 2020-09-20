package be.woutschoovaerts.mollie.data.subscription;

import be.woutschoovaerts.mollie.data.common.Amount;
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
public class UpdateSubscriptionRequest {

    private Optional<Amount> amount = Optional.empty();

    private OptionalInt times = OptionalInt.empty();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Optional<Date> startDate = Optional.empty();

    private Optional<String> description = Optional.empty();

    private Optional<String> webhookUrl = Optional.empty();

    private Map<String, Object> metadata;
}
