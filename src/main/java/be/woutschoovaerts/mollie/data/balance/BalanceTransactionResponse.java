package be.woutschoovaerts.mollie.data.balance;

import be.woutschoovaerts.mollie.data.common.Amount;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BalanceTransactionResponse {

    private String resource;

    private String id;

    private BalanceTransactionType type;

    private Amount resultAmount;

    private Amount initialAmount;

    private Amount deductions;

    private LocalDateTime createdAt;

    private Map<String, String> context;

    @JsonProperty("_links")
    private BalanceLinks links;

}
