package be.woutschoovaerts.mollie.data.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientResponse {

    private String resource;

    private String id;

    private OffsetDateTime organizationCreatedAt;

    @JsonProperty("_links")
    private ClientLinks links;
}
