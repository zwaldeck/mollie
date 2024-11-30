package be.woutschoovaerts.mollie.data.organization;

import be.woutschoovaerts.mollie.data.common.AddressResponse;
import be.woutschoovaerts.mollie.data.common.Locale;
import be.woutschoovaerts.mollie.data.links.OrganizationLinks;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrganizationResponse {

    private String resource;

    private String id;

    private String name;

    private String email;

    private Locale locale;

    private AddressResponse address;

    private String registrationNumber;

    @Builder.Default
    private Optional<String> vatNumber = Optional.empty();

    @Builder.Default
    private Optional<String> vatRegulation = Optional.empty();

    @JsonProperty("_links")
    private OrganizationLinks links;

}
