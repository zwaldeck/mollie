package be.feelio.mollie.data.request;

import be.feelio.mollie.data.common.Amount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderLineUpdateRequest {

    @Builder.Default
    private Optional<String> name = Optional.empty();

    @Builder.Default
    private Optional<String> imageUrl = Optional.empty();

    @Builder.Default
    private Optional<Integer> quantity = Optional.empty();

    @Builder.Default
    private Optional<Amount> unitPrice = Optional.empty();

    @Builder.Default
    private Optional<Amount> discountAmount = Optional.empty();

    @Builder.Default
    private Optional<Amount> totalAmount = Optional.empty();

    @Builder.Default
    private Optional<Amount> vatAmount = Optional.empty();

    @Builder.Default
    private Optional<String> vatRate = Optional.empty();

    @Builder.Default
    private Optional<Boolean> testmode = Optional.empty();

}
