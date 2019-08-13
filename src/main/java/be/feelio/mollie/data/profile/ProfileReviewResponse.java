package be.feelio.mollie.data.profile;

import be.feelio.mollie.data.enums.ProfileReviewStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileReviewResponse {

    private ProfileReviewStatus status;
}
