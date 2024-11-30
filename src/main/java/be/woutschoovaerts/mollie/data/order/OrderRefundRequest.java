package be.woutschoovaerts.mollie.data.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRefundRequest {

    @Builder.Default
    private Optional<String> description = Optional.empty();

    @JsonInclude(JsonInclude.Include.ALWAYS)
    private List<OrderRefundLineRequest> lines;

    private Map<String, Object> metadata;

    @Builder.Default
    private Optional<Boolean> testmode = Optional.empty();

}
