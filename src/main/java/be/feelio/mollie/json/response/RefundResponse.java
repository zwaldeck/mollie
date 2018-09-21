package be.feelio.mollie.json.response;

import be.feelio.mollie.json.common.Amount;
import be.feelio.mollie.json.common.OrderLine;
import be.feelio.mollie.json.links.RefundLinks;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefundResponse {

    private String resource;

    private String id;

    private Amount amount;

    private Optional<Amount> settlementAmount;

    private String description;

    private String status;

    private Optional<List<OrderLine>> lines;

    private String paymentId;

    private Optional<String> orderId;

    private Date createdAt;

    @JsonProperty("_links")
    private RefundLinks links;
}
