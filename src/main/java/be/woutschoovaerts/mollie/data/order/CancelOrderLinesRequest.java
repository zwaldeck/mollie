package be.woutschoovaerts.mollie.data.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CancelOrderLinesRequest {

    @Builder.Default
    private List<CancelOrderLineRequest> lines = new ArrayList<>();

    @Builder.Default
    private Optional<Boolean> testmode = Optional.empty();
}
