package be.feelio.mollie.data.response;

import be.feelio.mollie.data.permission.PermissionListEmbedded;
import be.feelio.mollie.data.shipping.ShipmentListLinks;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionListResponse {

    private int count;

    @JsonProperty("_embedded")
    private PermissionListEmbedded embedded;

    @JsonProperty("_links")
    private ShipmentListLinks links;
}
