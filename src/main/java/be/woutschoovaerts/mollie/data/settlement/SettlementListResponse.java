package be.woutschoovaerts.mollie.data.settlement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SettlementListResponse {

    private List<SettlementResponse> settlements;

}
