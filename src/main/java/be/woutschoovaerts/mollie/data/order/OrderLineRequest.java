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
public class OrderLineRequest implements OrderLineOperationDataRequest {

    @Builder.Default
    private Optional<OrderLineType> type = Optional.empty();

    @Builder.Default
    private Optional<OrderLineCategory> category = Optional.empty();

    private String name;

    private int quantity;

    private Amount unitPrice;

    @Builder.Default
    private Optional<Amount> discountAmount = Optional.empty();

    private Amount totalAmount;

    private String vatRate;

    private Amount vatAmount;

    @Builder.Default
    private Optional<String> sku = Optional.empty();

    @Builder.Default
    private Optional<String> imageUrl = Optional.empty();

    @Builder.Default
    private Optional<String> productUrl = Optional.empty();

    @Builder.Default
    private Optional<Map<String, Object>> metadata = Optional.empty();
}
