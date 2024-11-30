package be.woutschoovaerts.mollie.data.client.link;

import be.woutschoovaerts.mollie.data.common.Link;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientLinkLinks {

    private Link self;

    private Link clientLink;

    private Link documentation;
}
