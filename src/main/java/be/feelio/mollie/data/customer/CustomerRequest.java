package be.feelio.mollie.data.customer;

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

    private Optional<String> locale;

    private Map<String, Object> metadata;
}
