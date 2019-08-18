package be.feelio.mollie.data.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileRequest {

    private String name;

    private String website;

    private String email;

    private String phone;

    @Builder.Default
    private Optional<Integer> categoryCode = Optional.empty();

    @Builder.Default
    private Optional<String> mode = Optional.empty();
}
