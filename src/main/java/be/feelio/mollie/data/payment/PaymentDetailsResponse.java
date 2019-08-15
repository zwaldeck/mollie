package be.feelio.mollie.data.payment;

import be.feelio.mollie.data.common.Amount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDetailsResponse {

    // we make everything optional because we cannot now what payment method is used

    //  TODO support for QR code

    private Optional<String> cardNumber;

    private Optional<String> cardFingerprint;

    private Optional<String> bankname;

    private Optional<String> bankAccount;

    private Optional<String> bankBic;

    private Optional<String> transferReference;

    private Optional<String> consumerName;

    private Optional<String> consumerAccount;

    private Optional<String> consumerBic;

    private Optional<String> billingEmail;

    private Optional<String> bitcoinAddress;

    private Optional<Amount> bitcoinAmount;

    private Optional<String> bitcoinUri;

    private Optional<String> cardHolder;

    private Optional<String> carNumber;

    private Optional<String> cardAudience;

    private Optional<String> cardLabel;

    private Optional<String> cardCountryCode;

    private Optional<String> cardSecurity;

    private Optional<String> feeRegion;

    private Optional<String> failureReason;

    private Optional<String> voucherNumber;

    private List<GiftCardResponse> giftcards;

    private Optional<Amount> remainderAmount;

    private Optional<String> remainderMethod;

    private Optional<String> paypalReference;

    private Optional<String> customerReference;

    private Optional<String> creditorIdentifier;

    private Optional<Date> dueDate;

    private Optional<Date> signatureDate;

    private Optional<String> bankReasonCode;

    private Optional<String> bankReason;

    private Optional<String> endToEndIdentifier;

    private Optional<String> mandateReference;

    private Optional<String> batchReference;

    private Optional<String> fileReference;

}
