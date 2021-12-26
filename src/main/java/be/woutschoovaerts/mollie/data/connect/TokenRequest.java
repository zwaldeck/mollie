package be.woutschoovaerts.mollie.data.connect;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenRequest {

    @JsonProperty("grant_type")
    private GrantType grantType;

    @Builder.Default
    private Optional<String> code = Optional.empty();

    @JsonProperty("refresh_token")
    @Builder.Default
    private Optional<String> refreshToken = Optional.empty();

    @JsonProperty("redirect_uri")
    @Builder.Default
    private Optional<String> redirectUri = Optional.empty();
}
