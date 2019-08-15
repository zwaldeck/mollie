package be.feelio.mollie.data.shipment;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipmentRequest {

    @JsonInclude(JsonInclude.Include.ALWAYS)
    private List<ShipmentLineRequest> lines;

    @Builder.Default
    private Optional<ShipmentTrackingRequest> tracking = Optional.empty();

    @Builder.Default
    private Optional<Boolean> testmode = Optional.empty();
}
