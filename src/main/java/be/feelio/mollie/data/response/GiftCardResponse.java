package be.feelio.mollie.data.response;

import be.feelio.mollie.data.common.Amount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GiftCardResponse {

    private String issuer;

    private Amount amount;

    private String voucherNumber;
}
