package be.woutschoovaerts.mollie.data.balance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BalancesListResponse {

    private List<BalanceResponse> balances;
}
