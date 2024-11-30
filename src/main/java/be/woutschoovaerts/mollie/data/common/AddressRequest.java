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
public class AddressRequest {

    private String givenName;

    private String familyName;

    private String streetAndNumber;

    @Builder.Default
    private Optional<String> streetAdditional = Optional.empty();

    @Builder.Default
    private Optional<String> postalCode = Optional.empty();

    private String city;

    @Builder.Default
    private Optional<String> region = Optional.empty();

    private String country;

    private String email;

    private String phone;
}
