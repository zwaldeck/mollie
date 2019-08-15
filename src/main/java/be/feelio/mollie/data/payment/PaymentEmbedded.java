package be.feelio.mollie.data.payment;

import be.feelio.mollie.data.chargeback.ChargebackResponse;
import be.feelio.mollie.data.refund.RefundResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentEmbedded {

    private List<RefundResponse> refunds;

    private List<ChargebackResponse> chagebacks;
}
