package be.woutschoovaerts.mollie.data.common;

import be.woutschoovaerts.mollie.serializer.AmountSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonSerialize(using = AmountSerializer.class)
public class Amount {

    private String currency;

    private BigDecimal value;
}
