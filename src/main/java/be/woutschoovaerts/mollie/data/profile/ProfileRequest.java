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

    /**
     * @deprecated This parameter is deprecated and will be (is ?) removed in 2022. Use the businessCategory parameter instead.
     */
    @Builder.Default
    @Deprecated
    private Optional<Integer> categoryCode = Optional.empty();

    @Builder.Default
    private Optional<BusinessCategory> businessCategory = Optional.empty();

    @Builder.Default
    private Optional<String> mode = Optional.empty();
}
