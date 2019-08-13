package be.feelio.mollie.data.profile;

import be.feelio.mollie.data.response.ProfileResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileListEmbedded {

    private List<ProfileResponse> profiles;
}
