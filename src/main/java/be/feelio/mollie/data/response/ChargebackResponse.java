package be.feelio.mollie.data.response;

import be.feelio.mollie.data.common.Amount;
import be.feelio.mollie.data.links.ChargebackLinks;
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
public class ChargebackResponse {

    private String id;

    private Amount amount;

    private Amount settlementAmount;

    private Date createdAt;

    private Date reversedAt;

    private String paymentId;

    @JsonProperty("_links")
    private ChargebackLinks links;

    private Optional<PaymentResponse> payment;
}
