package be.feelio.mollie.data.settlement;

import be.feelio.mollie.data.response.SettlementResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SettlementListEmbedded {

    private List<SettlementResponse> settlements;
}
