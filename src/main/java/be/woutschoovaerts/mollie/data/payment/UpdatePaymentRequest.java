package be.woutschoovaerts.mollie.data.payment;

import be.woutschoovaerts.mollie.data.common.Locale;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatePaymentRequest {

    @Builder.Default
    private Optional<String> description = Optional.empty();

    @Builder.Default
    private Optional<String> redirectUrl = Optional.empty();

    @Builder.Default
    private Optional<String> cancelUrl = Optional.empty();

    @Builder.Default
    private Optional<String> webhookUrl = Optional.empty();

    @Builder.Default
    private Optional<Map<String, Object>> metadata = Optional.empty();

    @Builder.Default
    private Optional<PaymentMethod> method = Optional.empty();

    @Builder.Default
    private Optional<Locale> locale = Optional.empty();

    @Builder.Default
    private Optional<String> restrictPaymentMethodsToCountry = Optional.empty();

}
