package be.feelio.mollie.json.request;

import be.feelio.mollie.json.common.Amount;
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

    private Optional<String> locale;

    private Optional<String> method;

    private Map<String, Object> metadata;

    private Optional<String> sequenceType;

    private Optional<String> customerId;

    private Optional<String> mandateId;

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

    // TODO support Mollie Connect/OAuth parameters
    // TODO support QR codes
}
