package be.woutschoovaerts.mollie.data.refund;

import be.woutschoovaerts.mollie.data.common.Amount;
import be.woutschoovaerts.mollie.data.links.RefundLinks;
import be.woutschoovaerts.mollie.data.order.OrderLineResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefundResponse {

    private String resource;

    private String id;

    private Amount amount;

    @Builder.Default
    private Optional<String> settlementId = Optional.empty();

    @Builder.Default
    private Optional<Amount> settlementAmount = Optional.empty();

    private String description;

    private HashMap<String, Object> metadata;

    private String status;

    @Builder.Default
    private List<OrderLineResponse> lines = new ArrayList<>();

    private String paymentId;

    @Builder.Default
    private Optional<String> orderId = Optional.empty();

    private OffsetDateTime createdAt;

    @Builder.Default
    @JsonProperty("_embedded")
    private Optional<RefundEmbedded> embedded = Optional.empty();

    @JsonProperty("_links")
    private RefundLinks links;
}
