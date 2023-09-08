package be.woutschoovaerts.mollie.data.order;

import be.woutschoovaerts.mollie.data.common.Amount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateOrderLineRequest implements OrderLineOperationDataRequest {

    private String id;

    @Builder.Default
    private Optional<String> name = Optional.empty();

    @Builder.Default
    private Optional<String> sku = Optional.empty();

    @Builder.Default
    private Optional<String> imageUrl = Optional.empty();

    @Builder.Default
    private Optional<String> productUrl = Optional.empty();

    @Builder.Default
    private Optional<Integer> quantity = Optional.empty();

    @Builder.Default
    private Optional<Amount> unitPrice = Optional.empty();

    @Builder.Default
    private Optional<Amount> discountPrice = Optional.empty();

    @Builder.Default
    private Optional<String> vatRate = Optional.empty();

    @Builder.Default
    private Optional<Amount> vatAmount = Optional.empty();

    @Builder.Default
    private Optional<Amount> totalAmount = Optional.empty();

    @Builder.Default
    private Optional<Map<String, Object>> metadata = Optional.empty();
}
