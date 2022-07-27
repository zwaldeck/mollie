package be.woutschoovaerts.mollie.data.chargeback;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChargebackReason {

    private String code;

    private String description;

}