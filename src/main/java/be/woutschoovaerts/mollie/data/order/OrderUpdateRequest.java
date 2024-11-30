package be.woutschoovaerts.mollie.data.order;

import be.woutschoovaerts.mollie.data.common.AddressRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderUpdateRequest {

    @Builder.Default
    private Optional<String> orderNumber = Optional.empty();

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

    @Builder.Default
    private Optional<Boolean> testmode = Optional.empty();

}
