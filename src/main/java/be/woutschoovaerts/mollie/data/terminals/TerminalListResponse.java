package be.woutschoovaerts.mollie.data.terminals;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TerminalListResponse {

    private List<Terminal> terminals;
}
