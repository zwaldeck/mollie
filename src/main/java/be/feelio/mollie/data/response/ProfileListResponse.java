package be.feelio.mollie.data.response;

import be.feelio.mollie.data.profile.ProfileListEmbedded;
import be.feelio.mollie.data.profile.ProfileListLinks;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileListResponse {

    private int count;

    @JsonProperty("_embedded")
    private ProfileListEmbedded embedded;

    @JsonProperty("_links")
    private ProfileListLinks links;
}
