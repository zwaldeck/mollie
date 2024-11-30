package be.woutschoovaerts.mollie.data.mandate;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class MandateRequest {

    private MandatePaymentMethod method;

    private String consumerName;

    @Builder.Default
    private Optional<String> consumerAccount = Optional.empty();

    @Builder.Default
    private Optional<String> consumerBic = Optional.empty();

    @Builder.Default
    private Optional<String> consumerEmail = Optional.empty();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Builder.Default
    private Optional<OffsetDateTime> signatureDate = Optional.empty();

    @Builder.Default
    private Optional<String> mandateReference = Optional.empty();

    @Builder.Default
    private Optional<String> paypalBillingAgreementId = Optional.empty();

    @Builder.Default
    private Optional<Boolean> testmode = Optional.empty();
}
