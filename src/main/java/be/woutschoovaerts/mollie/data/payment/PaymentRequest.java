package be.woutschoovaerts.mollie.data.payment;

import be.woutschoovaerts.mollie.data.common.AddressRequest;
import be.woutschoovaerts.mollie.data.common.Amount;
import be.woutschoovaerts.mollie.data.common.ApplicationFee;
import be.woutschoovaerts.mollie.data.common.Locale;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {

    private Amount amount;

    private String description;

    private Optional<String> redirectUrl;

    private Optional<String> webhookUrl;

    private Optional<Locale> locale;

    private Optional<PaymentMethod> method;

    private Map<String, Object> metadata;

    private Optional<SequenceType> sequenceType;

    private Optional<String> customerId;

    private Optional<String> mandateId;

    // payment method specific parameters

    private Optional<String> cardToken;

    private Optional<String> billingEmail;

    private Optional<Date> dueDate;

    private Optional<AddressRequest> billingAddress;

    private Optional<AddressRequest> shippingAddress;

    private Optional<String> issuer;

    private Optional<String> voucherNumber;

    private Optional<String> voucherPin;

    private Optional<String> customerReference;

    private Optional<String> consumerName;

    private Optional<String> consumerAccount;

    // OAuth params
    private Optional<String> profileId;

    private Optional<ApplicationFee> applicationFee;
}
