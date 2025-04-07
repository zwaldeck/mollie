package be.woutschoovaerts.mollie.data.client.link;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Locale;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientLinkOwnerRequest {

    private String email;
    private String givenName;
    private String familyName;

    @Builder.Default
    private Optional<Locale> locale = Optional.empty();

}
