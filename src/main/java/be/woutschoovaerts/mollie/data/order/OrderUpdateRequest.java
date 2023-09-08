package be.woutschoovaerts.mollie.data.order;

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
    private Optional<OrderAddressRequest> billingAddress = Optional.empty();

    @Builder.Default
    private Optional<OrderAddressRequest> shippingAddress = Optional.empty();

    @Builder.Default
    private Optional<String> orderNumber = Optional.empty();

    @Builder.Default
    private Optional<String> redirectUrl = Optional.empty();

    @Builder.Default
    private Optional<String> cancelUrl = Optional.empty();

    @Builder.Default
    private Optional<String> webhookUrl = Optional.empty();

    @Builder.Default
    private Optional<Boolean> testmode = Optional.empty();

}
