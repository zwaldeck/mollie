package be.woutschoovaerts.mollie.data.paymentLink;

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
public class PaymentLinkListResponse {

    @JsonProperty("payment_links")
    private List<PaymentLinkResponse> paymentLinks;
}
