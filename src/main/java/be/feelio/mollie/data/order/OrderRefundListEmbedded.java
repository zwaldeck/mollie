package be.feelio.mollie.data.order;

import be.feelio.mollie.data.response.PaymentResponse;
import be.feelio.mollie.data.response.RefundResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRefundListEmbedded {

    private List<RefundResponse> refunds;


    private List<PaymentResponse> payments;

}
