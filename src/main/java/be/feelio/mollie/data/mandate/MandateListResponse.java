package be.feelio.mollie.data.mandate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MandateListResponse {

    private List<MandateResponse> mandates;
}
