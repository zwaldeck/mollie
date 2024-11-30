package be.woutschoovaerts.mollie.data.payment;

import be.woutschoovaerts.mollie.data.capture.CaptureResponse;
import be.woutschoovaerts.mollie.data.chargeback.ChargebackResponse;
import be.woutschoovaerts.mollie.data.refund.RefundResponse;
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

    private List<CaptureResponse> captures;

    private List<RefundResponse> refunds;

    private List<ChargebackResponse> chargebacks;
}
