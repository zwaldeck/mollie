package be.woutschoovaerts.mollie.data.client.link;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientLinkResponse {

    private String resource;

    private String id;

    @JsonProperty("_links")
    private ClientLinkLinks links;
}
