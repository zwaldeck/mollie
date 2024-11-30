package be.woutschoovaerts.mollie.data.method;

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
public class MethodPricing {

    private String description;

    private Amount fixed;

    private String variable;

    @Builder.Default
    private Optional<String> feeRegion = Optional.empty();
}
