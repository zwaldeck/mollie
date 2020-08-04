package be.woutschoovaerts.mollie.data.profile;

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
