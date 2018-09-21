package be.feelio.mollie.json.response;

import be.feelio.mollie.json.common.Amount;
import be.feelio.mollie.json.links.CaptureLinks;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CaptureResponse {

    private String resource;

    private String id;

    private String mode;

    private Amount amount;

    private Amount settlementAmount;

    private String paymentId;

    private Optional<String> shipmentId;

    private Optional<String> settlementId;

    private Date createdAt;

    @JsonProperty("_links")
    private CaptureLinks links;
}
