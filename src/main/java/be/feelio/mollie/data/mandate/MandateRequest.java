package be.feelio.mollie.data.mandate;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MandateRequest {

    private String method;

    private String consumerName;

    private String consumerAccount;

    private Optional<String> consumerBic;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Optional<Date> signatureDate;

    private Optional<String> mandateReference;
}
