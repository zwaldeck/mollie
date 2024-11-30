package be.woutschoovaerts.mollie.data.capture;

import be.woutschoovaerts.mollie.data.payment.PaymentResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CaptureEmbedded {

    private PaymentResponse payment;
}
