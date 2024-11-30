package be.woutschoovaerts.mollie.data.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressResponse {

    @Builder.Default
    private Optional<String> organizationName = Optional.empty();

    @Builder.Default
    private Optional<String> title = Optional.empty();

    private String givenName;

    private String familyName;

    private String email;

    @Builder.Default
    private Optional<String> phone = Optional.empty();

    private String streetAndNumber;

    @Builder.Default
    private Optional<String> streetAdditional = Optional.empty();

    private String postalCode;

    private String city;

    @Builder.Default
    private Optional<String> region = Optional.empty();

    private String country;

}
