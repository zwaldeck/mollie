package be.feelio.mollie.json.request;

import be.feelio.mollie.json.common.Amount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefundRequest {

    private Amount amount;

    private Optional<String> description;
}
