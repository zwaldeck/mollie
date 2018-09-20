package be.feelio.mollie.json.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginationLinks {

    private Link self;
    private Link previous;

    private Link next;

    private Link documentation;
}
