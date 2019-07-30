package be.feelio.mollie.data.links;

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
public class PaginationLinks {

    private Link self;

    private Optional<Link> previous;

    private Optional<Link> next;

    private Link documentation;
}
