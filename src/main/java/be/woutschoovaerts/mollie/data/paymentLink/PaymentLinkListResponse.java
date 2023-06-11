package be.woutschoovaerts.mollie.data.paymentLink;

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

    private List<PaymentLinkResponse> payments;
}
