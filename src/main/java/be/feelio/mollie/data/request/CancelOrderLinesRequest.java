package be.feelio.mollie.data.request;

import be.feelio.mollie.data.order.CancelOrderLineRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CancelOrderLinesRequest {

    @Builder.Default
    private List<CancelOrderLineRequest> lines = new ArrayList<>();
}
