package be.woutschoovaerts.mollie.data.links;

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
public class CaptureLinks {

    private Link self;

    private Link payment;

    private Optional<Link> settlement = Optional.empty();

    private Optional<Link> shipment = Optional.empty();

    private Link documentation;

}
