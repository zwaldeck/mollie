package be.woutschoovaerts.mollie.data.settlement;

import be.woutschoovaerts.mollie.data.common.Amount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PeriodCostRateResponse {

    private Amount fixed;


    private String variable;
}
