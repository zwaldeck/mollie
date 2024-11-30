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
public class UpdateProfileRequest {

    @Builder.Default
    private Optional<String> name = Optional.empty();

    @Builder.Default
    private Optional<String> website = Optional.empty();

    @Builder.Default
    private Optional<String> email = Optional.empty();

    @Builder.Default
    private Optional<String> phone = Optional.empty();

    @Builder.Default
    private Optional<String> description = Optional.empty();

    @Builder.Default
    private Optional<String[]> countriesOfActivity = Optional.empty();

    @Builder.Default
    private Optional<BusinessCategory> businessCategory = Optional.empty();

    @Builder.Default
    private Optional<Integer> categoryCode = Optional.empty();

    @Builder.Default
    private Optional<String> mode = Optional.empty();

}
