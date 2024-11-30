package be.woutschoovaerts.mollie.data.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentStatusReasonResponse {

    private String statusCode;

    private String statusMessage;
}
