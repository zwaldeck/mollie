package be.feelio.mollie.data.shipping;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipmentTrackingRequest {

    private String carrier;

    private String code;

    @Builder.Default
    private Optional<String> url = Optional.empty();

}
