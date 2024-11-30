package be.woutschoovaerts.mollie.data.payment;

import be.woutschoovaerts.mollie.data.common.AddressRequest;
import be.woutschoovaerts.mollie.data.common.Amount;
import be.woutschoovaerts.mollie.data.common.ApplicationFee;
import be.woutschoovaerts.mollie.data.common.Locale;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {

    private String description;

    private Amount amount;

    private String redirectUrl;

    @Builder.Default
    private Optional<String> cancelUrl = Optional.empty();

    @Builder.Default
    private Optional<String> webhookUrl = Optional.empty();

    @Builder.Default
    private Optional<List<PaymentLineRequest>> lines = Optional.empty();

    @Builder.Default
    private Optional<AddressRequest> billingAddress = Optional.empty();

    @Builder.Default
    private Optional<AddressRequest> shippingAddress = Optional.empty();

    @Builder.Default
    private Optional<Locale> locale = Optional.empty();

    @Builder.Default
    private Optional<List<PaymentMethod>> method = Optional.empty();

    @Builder.Default
    private Optional<String> issuer = Optional.empty();

    @Builder.Default
    private Optional<String> restrictPaymentMethodsToCountry = Optional.empty();

    private Map<String, Object> metadata;

    @Builder.Default
    private Optional<PaymentCaptureMode> captureMode = Optional.empty();

    @Builder.Default
    private Optional<String> captureDelay = Optional.empty();

    @Builder.Default
    private Optional<ApplicationFee> applicationFee = Optional.empty();

    @Builder.Default
    private Optional<List<PaymentRouteRequest>> routing = Optional.empty();

    @Builder.Default
    private Optional<SequenceType> sequenceType = Optional.empty();

    @Builder.Default
    private Optional<String> mandateId = Optional.empty();

    @Builder.Default
    private Optional<String> customerId = Optional.empty();

    @Builder.Default
    private Optional<String> profileId = Optional.empty();

    @Builder.Default
    private Optional<Boolean> testmode = Optional.empty();

    @Builder.Default
    private Optional<String> applePayPaymentToken = Optional.empty();

    @Builder.Default
    private Optional<OffsetDateTime> dueDate = Optional.empty();

    @Builder.Default
    private Optional<String> company = Optional.empty();

    @Builder.Default
    private Optional<String> cardToken = Optional.empty();

    @Builder.Default
    private Optional<String> voucherNumber = Optional.empty();

    @Builder.Default
    private Optional<String> voucherPin = Optional.empty();

    @Builder.Default
    private Optional<String> consumerDateOfBirth = Optional.empty();

    @Builder.Default
    private Optional<Map<String, Object>> extraMerchantData = Optional.empty();

    @Builder.Default
    private Optional<String> sessionId = Optional.empty();

    @Builder.Default
    private Optional<Boolean> digitalGoods = Optional.empty();

    @Builder.Default
    private Optional<String> customerReference = Optional.empty();

    @Builder.Default
    private Optional<String> terminalId = Optional.empty();

}
