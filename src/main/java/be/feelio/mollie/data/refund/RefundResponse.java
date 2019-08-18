package be.feelio.mollie.data.refund;

import be.feelio.mollie.data.common.Amount;
import be.feelio.mollie.data.links.RefundLinks;
import be.feelio.mollie.data.order.OrderLineResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
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

    private Optional<String> settlementId;

    private Optional<Amount> settlementAmount;

    private String description;

    private HashMap<String, Object> metedata;

    private String status;

    private Optional<List<OrderLineResponse>> lines;

    private String paymentId;

    private Optional<String> orderId;

    private Date createdAt;

    @JsonProperty("_embedded")
    private Optional<RefundEmbedded> embedded;

    @JsonProperty("_links")
    private RefundLinks links;
}
