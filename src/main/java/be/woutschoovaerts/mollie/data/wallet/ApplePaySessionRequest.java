package be.woutschoovaerts.mollie.data.wallet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplePaySessionRequest {

    /**
     * The validationUrl you got from the <a href="https://developer.apple.com/documentation/apple_pay_on_the_web/applepayvalidatemerchantevent"></a>ApplePayValidateMerchant</a> event.
     */
    private String validationUrl;

    /**
     * The domain of your web shop, that is visible in the browser’s location bar. For example pay.myshop.com.
     */
    private String domain;

    @Builder.Default
    private Optional<String> profileId = Optional.empty();
}
