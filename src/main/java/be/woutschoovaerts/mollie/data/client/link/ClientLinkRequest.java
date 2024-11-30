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
public class ClientLinkRequest {

    private ClientLinkOwnerRequest owner;

    private String name;

    private ClientLinkAddressRequest address;

    @Builder.Default
    private Optional<String> registrationNumber = Optional.empty();

    @Builder.Default
    private Optional<String> vatNumber = Optional.empty();
}
