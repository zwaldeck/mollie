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
public class VoucherIssuerResponse {

    private String resource;

    private String id;

    private String description;

    private IssuerStatus status;

    private Contractor contractor;

    @JsonProperty("_links")
    private IssuerLinks links;
}
