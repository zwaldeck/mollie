package be.woutschoovaerts.mollie.data.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ManageOrderLineRequest {

    private List<OrderLineOperationRequest<?>> operations;

}
