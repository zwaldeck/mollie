package be.woutschoovaerts.mollie.data.payment;

import be.woutschoovaerts.mollie.data.common.Amount;
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
public class PaymentDetailsResponse {

    @Builder.Default
    private Optional<String> terminalId = Optional.empty();

    @Builder.Default
    private Optional<String> cardNumber = Optional.empty();

    @Builder.Default
    private Optional<String> cardFingerprint = Optional.empty();

    @Builder.Default
    private Optional<String> bankname = Optional.empty();

    @Builder.Default
    private Optional<String> bankAccount = Optional.empty();

    @Builder.Default
    private Optional<String> bankBic = Optional.empty();

    @Builder.Default
    private Optional<String> transferReference = Optional.empty();

    @Builder.Default
    private Optional<String> consumerName = Optional.empty();

    @Builder.Default
    private Optional<String> consumerAccount = Optional.empty();

    @Builder.Default
    private Optional<String> consumerBic = Optional.empty();

    @Builder.Default
    private Optional<String> billingEmail = Optional.empty();

    @Builder.Default
    private Optional<String> bitcoinAddress = Optional.empty();

    @Builder.Default
    private Optional<Amount> bitcoinAmount = Optional.empty();

    @Builder.Default
    private Optional<String> bitcoinUri = Optional.empty();

    @Builder.Default
    private Optional<String> cardHolder = Optional.empty();

    @Builder.Default
    private Optional<String> carNumber = Optional.empty();

    @Builder.Default
    private Optional<String> cardAudience = Optional.empty();

    @Builder.Default
    private Optional<String> cardLabel = Optional.empty();

    @Builder.Default
    private Optional<String> cardCountryCode = Optional.empty();

    @Builder.Default
    private Optional<String> cardSecurity = Optional.empty();

    @Builder.Default
    private Optional<String> feeRegion = Optional.empty();

    @Builder.Default
    private Optional<String> failureReason = Optional.empty();

    @Builder.Default
    private Optional<String> voucherNumber = Optional.empty();

    private List<GiftCardResponse> giftcards;

    @Builder.Default
    private Optional<List<Map<String, Object>>> vouchers = Optional.empty();

    @Builder.Default
    private Optional<Amount> remainderAmount = Optional.empty();

    @Builder.Default
    private Optional<String> remainderMethod = Optional.empty();

    @Builder.Default
    private Optional<Map<String, Object>> remainderDetails = Optional.empty();

    @Builder.Default
    private Optional<String> paypalReference = Optional.empty();

    @Builder.Default
    private Optional<String> customerReference = Optional.empty();

    @Builder.Default
    private Optional<String> creditorIdentifier = Optional.empty();

    @Builder.Default
    private Optional<LocalDate> dueDate = Optional.empty();

    @Builder.Default
    private Optional<LocalDate> signatureDate = Optional.empty();

    @Builder.Default
    private Optional<String> bankReasonCode = Optional.empty();

    @Builder.Default
    private Optional<String> bankReason = Optional.empty();

    @Builder.Default
    private Optional<String> endToEndIdentifier = Optional.empty();

    @Builder.Default
    private Optional<String> mandateReference = Optional.empty();

    @Builder.Default
    private Optional<String> batchReference = Optional.empty();

    @Builder.Default
    private Optional<String> fileReference = Optional.empty();

    @Builder.Default
    private Optional<QrCode> qrCode = Optional.empty();

}
