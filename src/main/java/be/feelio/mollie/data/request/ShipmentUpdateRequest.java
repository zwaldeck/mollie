package be.feelio.mollie.data.request;

import be.feelio.mollie.data.shipping.ShipmentTrackingRequest;
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
