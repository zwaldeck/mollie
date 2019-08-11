package be.feelio.mollie.data.order;

import be.feelio.mollie.data.response.OrderResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderListEmbedded {

    private List<OrderResponse> orders;

}
