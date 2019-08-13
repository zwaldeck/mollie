package be.feelio.mollie.data.response;

import be.feelio.mollie.data.settlement.SettlementListEmbedded;
import be.feelio.mollie.data.settlement.SettlementListLinks;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SettlementListResponse {

    private int count;

    @JsonProperty("_embedded")
    private SettlementListEmbedded embedded;

    @JsonProperty("_links")
    private SettlementListLinks links;
}
