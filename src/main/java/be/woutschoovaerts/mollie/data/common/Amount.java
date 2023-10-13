package be.woutschoovaerts.mollie.data.common;

import be.woutschoovaerts.mollie.serializer.MoneySerializer;
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
public class Amount {

    private String currency;

    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal value;
}
