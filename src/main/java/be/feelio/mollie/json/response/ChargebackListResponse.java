package be.feelio.mollie.json.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChargebackListResponse {

    private List<ChargebackResponse> chargebacks;
}
