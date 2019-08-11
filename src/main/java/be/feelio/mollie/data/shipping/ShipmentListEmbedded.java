package be.feelio.mollie.data.shipping;

import be.feelio.mollie.data.response.ShipmentResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipmentListEmbedded {

    private List<ShipmentResponse> orders;

}
