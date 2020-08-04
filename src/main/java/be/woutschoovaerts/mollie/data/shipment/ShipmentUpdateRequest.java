package be.woutschoovaerts.mollie.data.shipment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipmentUpdateRequest {

    private ShipmentTrackingRequest tracking;

    @Builder.Default
    private Optional<Boolean> testmode = Optional.empty();
}
