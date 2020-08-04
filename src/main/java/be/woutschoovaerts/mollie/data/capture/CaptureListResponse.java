package be.woutschoovaerts.mollie.data.capture;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CaptureListResponse {

    private List<CaptureResponse> captures;
}
