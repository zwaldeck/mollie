package be.woutschoovaerts.mollie.data.balance;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BalanceTransactionsListResponse {

    @JsonProperty("balance_transactions")
    private List<BalanceTransactionResponse> transactions;
}
