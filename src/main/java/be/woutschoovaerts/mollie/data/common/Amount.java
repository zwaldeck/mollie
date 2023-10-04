package be.woutschoovaerts.mollie.data.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Amount {

    private String currency;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal value;
}
