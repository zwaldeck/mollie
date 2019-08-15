package be.feelio.mollie.data.settlement;

import be.feelio.mollie.data.common.Amount;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SettlementResponse {

    private String resource;

    private String id;

    private String reference;

    private Date createdAt;

    private Date settledAt;

    private SettlementStatus status;

    private Amount amount;

    private HashMap<Integer, HashMap<Integer, PeriodResponse>> periods;

    private String invoiceId;

    @JsonProperty("_links")
    private SettlementLinks links;
}
