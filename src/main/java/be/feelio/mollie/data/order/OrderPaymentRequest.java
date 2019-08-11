package be.feelio.mollie.data.order;

import be.feelio.mollie.data.common.ApplicationFee;
import be.feelio.mollie.data.enums.PaymentMethod;
import be.feelio.mollie.serializer.PaymentMethodSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderPaymentRequest {

    @Builder.Default
    @JsonSerialize(using = PaymentMethodSerializer.class)
    private Optional<List<PaymentMethod>> method = Optional.empty();

    @Builder.Default
    private Optional<String> customerId = Optional.empty();


    @Builder.Default
    private Optional<String> mandateId = Optional.empty();

    @Builder.Default
    private Optional<String> consumerAccount = Optional.empty();

    @Builder.Default
    private Optional<String> customerReference = Optional.empty();

    @Builder.Default
    private Optional<String> issuer = Optional.empty();

    @Builder.Default
    private Optional<String> sequenceType = Optional.empty();

    @Builder.Default
    private Optional<String> voucherNumber = Optional.empty();

    @Builder.Default
    private Optional<String> voucherPin = Optional.empty();

    @Builder.Default
    private Optional<String> webhookUrl = Optional.empty();

    @Builder.Default
    private Optional<Boolean> testmode = Optional.empty();

    @Builder.Default
    private Optional<ApplicationFee> applicationFee = Optional.empty();

}
