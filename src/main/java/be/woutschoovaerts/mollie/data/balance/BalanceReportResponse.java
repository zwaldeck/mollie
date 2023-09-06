package be.woutschoovaerts.mollie.data.balance;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BalanceReportResponse {

    private String resource;

    private String balanceId;

    private String timeZone;

    private LocalDate from;

    private LocalDate until;

    private String grouping;

    private Map<String, Object> totals;

    @JsonProperty("_links")
    private BalanceLinks links;

}
