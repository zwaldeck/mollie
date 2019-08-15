package be.feelio.mollie.data.common;

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

    private String streetAndNumber;

    private Optional<String> streetAdditional;

    private String postalCode;

    private String city;

    private Optional<String> region;

    private String country;
}
