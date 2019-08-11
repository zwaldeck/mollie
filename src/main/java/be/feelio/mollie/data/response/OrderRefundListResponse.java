package be.feelio.mollie.data.response;

import be.feelio.mollie.data.order.OrderRefundListEmbedded;
import be.feelio.mollie.data.order.OrderRefundListLinks;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRefundListResponse {

    private int count;

    @JsonProperty("_embedded")
    private OrderRefundListEmbedded embedded;

    @JsonProperty("_links")
    private OrderRefundListLinks links;
}
