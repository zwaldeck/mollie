package be.woutschoovaerts.mollie.data.shipment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipmentTrackingRequest {

    private String carrier;

    private String code;

    private String url;

}
