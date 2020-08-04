package be.woutschoovaerts.mollie.data.shipment;

import be.woutschoovaerts.mollie.data.common.Amount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipmentLineRequest {

    private String id;

    @Builder.Default
    private Optional<Integer> quantity = Optional.empty();

    @Builder.Default
    private Optional<Amount> amount = Optional.empty();


}
