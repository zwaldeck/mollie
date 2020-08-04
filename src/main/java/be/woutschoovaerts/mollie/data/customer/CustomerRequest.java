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

    private Optional<String> name;

    private Optional<String> email;

    private Optional<Locale> locale;

    private Map<String, Object> metadata;
}
