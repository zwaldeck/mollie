package be.feelio.mollie.data.settlement;

import be.feelio.mollie.data.common.Amount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PeriodCostResponse {

    private String description;

    private Amount amountNet;

    private Amount amountVat;

    private Amount amountGross;

    private int count;

    private PeriodCostRateResponse rate;

    private String method;
}
