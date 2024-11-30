package be.woutschoovaerts.mollie.data.organization;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrganizationPartnerResponse {

    private String resource;

    @Builder.Default
    private Optional<PartnerType> partnerType = Optional.empty();

    private boolean isCommissionPartner;

    private List<UserAgentTokenResponse> userAgentTokens;

    @Builder.Default
    private Optional<OffsetDateTime> partnerContractSignedAt = Optional.empty();

    private boolean partnerContractUpdateAvailable;

    @JsonProperty("_links")
    private OrganizationPartnerLinks links;

}
