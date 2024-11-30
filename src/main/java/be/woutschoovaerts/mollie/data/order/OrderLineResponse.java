package be.woutschoovaerts.mollie.data.order;

import be.woutschoovaerts.mollie.data.common.Amount;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderLineResponse {

    private String resource;

    private String id;

    private OrderLineType type;

    private String name;

    private int quantity;

    @Builder.Default
    private Optional<String> quantityUnit = Optional.empty();

    private Amount unitPrice;

    @Builder.Default
    private Optional<Amount> discountAmount = Optional.empty();

    private Amount totalAmount;

    private String vatRate;

    private Amount vatAmount;

    @Builder.Default
    private Optional<String> sku = Optional.empty();

    @Builder.Default
    private Optional<OrderLineCategory> category = Optional.empty();

    private OrderStatus status;

    private boolean isCancelable;

    @Builder.Default
    private Optional<Map<String, Object>> metadata = Optional.empty();

    private String orderId;

    private OffsetDateTime createdAt;

    @JsonProperty("_links")
    private OrderLineLinks links;

}
