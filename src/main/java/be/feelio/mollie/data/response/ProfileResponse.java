package be.feelio.mollie.data.response;

import be.feelio.mollie.data.enums.ProfileStatus;
import be.feelio.mollie.data.profile.ProfileLinks;
import be.feelio.mollie.data.profile.ProfileReviewResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileResponse {

    private String resource;

    private String id;

    private String name;

    private String website;

    private String mode;

    private String email;

    private String phone;

    private int categoryCode;

    private ProfileStatus status;

    private ProfileReviewResponse review;

    private Date createdAt;

    @JsonProperty("_links")
    private ProfileLinks links;
}
