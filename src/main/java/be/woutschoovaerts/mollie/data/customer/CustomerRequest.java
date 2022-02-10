package be.woutschoovaerts.mollie.data.customer;

import be.woutschoovaerts.mollie.data.common.Locale;
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
public class CustomerRequest {

    @Builder.Default
    private Optional<String> name = Optional.empty();

    @Builder.Default
    private Optional<String> email = Optional.empty();

    @Builder.Default
    private Optional<Locale> locale = Optional.empty();

    private Map<String, Object> metadata;
}
