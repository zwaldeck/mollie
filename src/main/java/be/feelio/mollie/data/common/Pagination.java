package be.feelio.mollie.data.common;

import be.feelio.mollie.data.links.PaginationLinks;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pagination<T> {

    private int count;

    @JsonProperty("_embedded")
    private T embedded;

    @JsonProperty("_links")
    private PaginationLinks links;
}
