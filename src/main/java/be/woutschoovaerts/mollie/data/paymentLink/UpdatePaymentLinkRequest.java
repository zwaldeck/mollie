package be.woutschoovaerts.mollie.data.paymentLink;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatePaymentLinkRequest {

    private String description;

    private boolean archived;
}
