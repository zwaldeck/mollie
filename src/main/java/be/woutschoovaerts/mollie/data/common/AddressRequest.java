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

    private Optional<String> streetAdditional = Optional.empty();

    private Optional<String> postalCode;

    private String city;

    private Optional<String> region = Optional.empty();

    private String country;

    private String email;

    private String phone;
}
