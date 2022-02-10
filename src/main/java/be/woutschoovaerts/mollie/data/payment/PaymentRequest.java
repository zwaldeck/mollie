package be.woutschoovaerts.mollie.data.payment;

import be.woutschoovaerts.mollie.data.common.AddressRequest;
import be.woutschoovaerts.mollie.data.common.Amount;
import be.woutschoovaerts.mollie.data.common.ApplicationFee;
import be.woutschoovaerts.mollie.data.common.Locale;
import be.woutschoovaerts.mollie.serializer.PaymentMethodSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {

    private Amount amount;

    private String description;

    @Builder.Default
    private Optional<String> redirectUrl = Optional.empty();

    @Builder.Default
    private Optional<String> webhookUrl = Optional.empty();

    @Builder.Default
    private Optional<Locale> locale = Optional.empty();

    @Builder.Default
    @JsonSerialize(using = PaymentMethodSerializer.class)
    private List<PaymentMethod> method = new ArrayList<>();

    private Map<String, Object> metadata;

    @Builder.Default
    private Optional<SequenceType> sequenceType = Optional.empty();

    @Builder.Default
    private Optional<String> customerId = Optional.empty();

    @Builder.Default
    private Optional<String> mandateId = Optional.empty();

    // payment method specific parameters

    @Builder.Default
    private Optional<String> cardToken = Optional.empty();

    @Builder.Default
    private Optional<String> billingEmail = Optional.empty();

    @Builder.Default
    private Optional<LocalDate> dueDate = Optional.empty();

    @Builder.Default
    private Optional<AddressRequest> billingAddress = Optional.empty();

    @Builder.Default
    private Optional<AddressRequest> shippingAddress = Optional.empty();

    @Builder.Default
    private Optional<String> issuer = Optional.empty();

    @Builder.Default
    private Optional<String> voucherNumber = Optional.empty();

    @Builder.Default
    private Optional<String> voucherPin = Optional.empty();

    @Builder.Default
    private Optional<String> customerReference = Optional.empty();

    @Builder.Default
    private Optional<String> consumerName = Optional.empty();

    @Builder.Default
    private Optional<String> consumerAccount = Optional.empty();

    @Builder.Default
    private Optional<ApplicationFee> applicationFee = Optional.empty();

    @Builder.Default
    private Optional<String> applePayPaymentToken = Optional.empty();

    // OAuth params
    @Builder.Default
    private Optional<String> profileId = Optional.empty();
}
