package be.feelio.mollie.data.order;

import be.feelio.mollie.data.common.Link;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderLinks {

    @Builder.Default
    private Optional<Link> productUrl = Optional.empty();

    @Builder.Default
    private Optional<Link> imageUrl = Optional.empty();
}
