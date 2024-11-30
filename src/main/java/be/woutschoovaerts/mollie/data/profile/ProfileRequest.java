package be.woutschoovaerts.mollie.data.profile;

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
    private Optional<String> description = Optional.empty();

    @Builder.Default
    private Optional<String[]> countriesOfActivity = Optional.empty();

    @Builder.Default
    private Optional<BusinessCategory> businessCategory = Optional.empty();

}
