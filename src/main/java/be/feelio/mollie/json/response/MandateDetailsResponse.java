package be.feelio.mollie.json.response;

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
public class MandateDetailsResponse {

    private Optional<String> consumerName;

    private Optional<String> consumerAccount;

    private Optional<String> consumerBic;

    private Optional<String> cardHolder;

    private Optional<String> cardNumber;

    private Optional<String> cardFingerprint;

    private Optional<Date> cardExpiryDate;

}
