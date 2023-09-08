package be.woutschoovaerts.mollie.data.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GiftCardIssuerResponse {

    private String resource;

    private String id;

    private String description;

    private IssuerStatus status;

    @JsonProperty("_links")
    private IssuerLinks links;
}
