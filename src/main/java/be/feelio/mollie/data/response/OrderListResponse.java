package be.feelio.mollie.data.response;

import be.feelio.mollie.data.order.OrderListEmbedded;
import be.feelio.mollie.data.order.OrderListLinks;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderListResponse {

    private int count;

    @JsonProperty("_embedded")
    private OrderListEmbedded embedded;

    @JsonProperty("_links")
    private OrderListLinks links;
}
