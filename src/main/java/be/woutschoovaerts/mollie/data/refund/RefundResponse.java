package be.woutschoovaerts.mollie.data.refund;

import be.woutschoovaerts.mollie.data.common.Amount;
import be.woutschoovaerts.mollie.data.common.RoutingReversalRequest;
import be.woutschoovaerts.mollie.data.links.RefundLinks;
import be.woutschoovaerts.mollie.data.order.OrderLineResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefundResponse {

    private String resource;

    private String id;

    private String mode;

    private String description;

    private Amount amount;

    @Builder.Default
    private Optional<Amount> settlementAmount = Optional.empty();

    @Builder.Default
    private Map<String, Object> metadata = new HashMap<>();

    private RefundStatus status;

    @Builder.Default
    private Optional<Boolean> reverseRouting = Optional.empty();

    @Builder.Default
    private Optional<RoutingReversalRequest> routingReversals = Optional.empty();

    private String paymentId;

    @Builder.Default
    private Optional<String> settlementId = Optional.empty();

    private OffsetDateTime createdAt;

    @JsonProperty("_links")
    private RefundLinks links;

    @Builder.Default
    private Optional<List<OrderLineResponse>> lines = Optional.empty();

    @Builder.Default
    private Optional<String> orderId = Optional.empty();


    @Builder.Default
    @JsonProperty("_embedded")
    private Optional<RefundEmbedded> embedded = Optional.empty();


}
