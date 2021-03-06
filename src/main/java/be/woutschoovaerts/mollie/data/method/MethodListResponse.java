package be.woutschoovaerts.mollie.data.method;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MethodListResponse {

    private List<MethodResponse> methods;
}
