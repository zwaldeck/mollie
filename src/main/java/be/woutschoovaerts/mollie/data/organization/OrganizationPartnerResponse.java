package be.woutschoovaerts.mollie.data.organization;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrganizationPartnerResponse {

    private String resource;

    private String id;

    private PartnerType partnerType;

    private Boolean isCommissionPartner;

    private List<UserAgentTokenResponse> userAgentTokens;

    private LocalDate partnerContractSignedAt;

    private Boolean partnerContractUpdateAvailable;

    @JsonProperty("_links")
    private OrganizationPartnerLinks links;

}
