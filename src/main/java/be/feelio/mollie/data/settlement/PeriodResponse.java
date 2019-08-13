package be.feelio.mollie.data.settlement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PeriodResponse {

    private List<PeriodRevenueResponse> revenue;

    private List<PeriodCostResponse> costs;
}
