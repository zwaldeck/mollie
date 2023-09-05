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

   private Optional<String> description;

    private Optional<String> redirectUrl;

    private Optional<String> cancelUrl;

    private Optional<String> webhookUrl;

    private Optional<Map<String, Object>> metadata;

    private Optional<Locale> locale;

    private Optional<String> restrictPaymentMethodsToCountry;

    private Optional<String> billingEmail;

    private Optional<String> dueDate;

    private Optional<String> issuer;
}
