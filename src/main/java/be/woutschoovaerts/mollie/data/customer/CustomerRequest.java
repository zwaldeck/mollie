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

    private Optional<String> name = Optional.empty();

    private Optional<String> email = Optional.empty();

    private Optional<Locale> locale = Optional.empty();

    private Map<String, Object> metadata;
}
