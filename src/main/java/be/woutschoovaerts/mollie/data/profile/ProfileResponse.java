package be.woutschoovaerts.mollie.data.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Optional;

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

    @Builder.Default
    private Optional<String> description = Optional.empty();

    private String[] countriesOfActivity;

    private BusinessCategory businessCategory;

    private ProfileStatus status;

    @Builder.Default
    private Optional<ProfileReviewResponse> review = Optional.empty();

    private OffsetDateTime createdAt;

    @JsonProperty("_links")
    private ProfileLinks links;
}
