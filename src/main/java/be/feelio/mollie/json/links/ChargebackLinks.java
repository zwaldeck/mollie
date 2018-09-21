package be.feelio.mollie.json.links;

import be.feelio.mollie.json.common.Link;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChargebackLinks {

    private Link self;

    private Link payment;

    private Optional<Link> settlement;

    private Link documentation;

}
