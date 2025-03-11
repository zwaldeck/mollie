package be.woutschoovaerts.mollie.data.settlement;

import be.woutschoovaerts.mollie.data.common.Amount;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SettlementResponse {

    private String resource;

    private String id;

    private String reference;

    private OffsetDateTime settledAt;

    private SettlementStatus status;

    private Amount amount;

    private String balanceId;

    private Map<Integer, Map<Integer, PeriodResponse>> periods;

    @JsonProperty("_links")
    private SettlementLinks links;
}
