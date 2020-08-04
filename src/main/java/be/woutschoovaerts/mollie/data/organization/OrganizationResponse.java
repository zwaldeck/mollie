package be.woutschoovaerts.mollie.data.organization;

import be.woutschoovaerts.mollie.data.common.AddressResponse;
import be.woutschoovaerts.mollie.data.common.Locale;
import be.woutschoovaerts.mollie.data.links.OrganizationLinks;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrganizationResponse {

    private String resource;

    private String id;

    private String name;

    private Locale locale;

    private AddressResponse address;

    private String registrationNumber;

    private String vatNumber;

    @JsonProperty("_links")
    private OrganizationLinks links;

}
