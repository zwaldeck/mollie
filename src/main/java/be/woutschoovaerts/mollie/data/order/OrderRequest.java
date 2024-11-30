package be.woutschoovaerts.mollie.data.order;

import be.woutschoovaerts.mollie.data.common.AddressRequest;
import be.woutschoovaerts.mollie.data.common.Amount;
import be.woutschoovaerts.mollie.data.common.Locale;
import be.woutschoovaerts.mollie.data.payment.PaymentMethod;
import be.woutschoovaerts.mollie.serializer.PaymentMethodSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {

    private String orderNumber;

    private List<OrderLineRequest> lines;

    private Amount amount;

    @Builder.Default
    private Optional<String> redirectUrl = Optional.empty();

    @Builder.Default
    private Optional<String> cancelUrl = Optional.empty();

    @Builder.Default
    private Optional<String> webhookUrl = Optional.empty();

    @Builder.Default
    private Optional<AddressRequest> billingAddress = Optional.empty();

    @Builder.Default
    private Optional<AddressRequest> shippingAddress = Optional.empty();

    private Locale locale;

    @Builder.Default
    @JsonSerialize(using = PaymentMethodSerializer.class)
    private Optional<List<PaymentMethod>> method = Optional.empty();

    @Builder.Default
    private Optional<Boolean> shopperCountryMustMatchBillingCountry = Optional.empty();

    private Map<String, Object> metadata;

    private Map<String, Object> payment;

    @Builder.Default
    private Optional<String> profileId = Optional.empty();

    @Builder.Default
    private Optional<LocalDate> expiresAt = Optional.empty();

    @Builder.Default
    private Optional<Boolean> testmode = Optional.empty();

}
