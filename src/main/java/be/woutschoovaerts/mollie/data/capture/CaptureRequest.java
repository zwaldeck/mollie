package be.woutschoovaerts.mollie.data.capture;

import be.woutschoovaerts.mollie.data.common.Amount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CaptureRequest {

    @Builder.Default
    private Optional<String> description = Optional.empty();

    @Builder.Default
    private Optional<Amount> amount = Optional.empty();

    private Map<String, Object> metadata;
}
