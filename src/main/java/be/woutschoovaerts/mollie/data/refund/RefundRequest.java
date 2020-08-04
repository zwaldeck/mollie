package be.woutschoovaerts.mollie.data.refund;

import be.woutschoovaerts.mollie.data.common.Amount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefundRequest {

    private Amount amount;

    private Optional<String> description;

    private HashMap<String, Object> metadata;
}
