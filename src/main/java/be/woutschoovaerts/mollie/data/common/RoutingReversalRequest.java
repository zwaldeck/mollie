package be.woutschoovaerts.mollie.data.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoutingReversalRequest {

    private Amount amount;
    private RoutingReversalSource source;

}
