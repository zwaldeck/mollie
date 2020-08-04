package be.woutschoovaerts.mollie.data.refund;

import be.woutschoovaerts.mollie.data.payment.PaymentResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefundEmbedded {

    private PaymentResponse payment;
}
