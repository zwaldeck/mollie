package be.woutschoovaerts.mollie.data.profile;

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
public class ProfileResponse {

    private String resource;

    private String id;

    private String mode;

    private String name;

    private String website;

    private String email;

    private String phone;

    @Deprecated
    private int categoryCode;

    private BusinessCategory businessCategory;

    private ProfileStatus status;

    private ProfileReviewResponse review;

    private OffsetDateTime createdAt;

    @JsonProperty("_links")
    private ProfileLinks links;
}
