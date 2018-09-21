package be.feelio.mollie.json.links;

import be.feelio.mollie.json.common.Link;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MethodLinks {

    private Link self;

    private Link documentation;

}
