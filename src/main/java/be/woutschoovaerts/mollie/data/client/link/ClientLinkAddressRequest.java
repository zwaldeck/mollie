package be.woutschoovaerts.mollie.data.client.link;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientLinkAddressRequest {

    @Builder.Default
    private Optional<String> streetAndNumber = Optional.empty();

    @Builder.Default
    private Optional<String> postalCode = Optional.empty();

    @Builder.Default
    private Optional<String> city = Optional.empty();

    private String country;

}
