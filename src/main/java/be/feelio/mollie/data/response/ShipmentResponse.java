package be.feelio.mollie.data.response;

import be.feelio.mollie.data.order.OrderLineResponse;
import be.feelio.mollie.data.shipping.ShipmentLinks;
import be.feelio.mollie.data.shipping.ShipmentTrackingResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipmentResponse {

    private String resource;

    private String id;

    private String orderId;

    private Date createdAt;

    private ShipmentTrackingResponse tracking;

    private List<OrderLineResponse> lines;

    @JsonProperty("_links")
    private ShipmentLinks links;
}
