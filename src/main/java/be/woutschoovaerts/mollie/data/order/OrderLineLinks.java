package be.woutschoovaerts.mollie.data.order;

import be.woutschoovaerts.mollie.data.common.Link;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderLineLinks {

    @Builder.Default
    private Optional<Link> self = Optional.empty();

    @Builder.Default
    private Optional<Link> imageUrl = Optional.empty();
}
