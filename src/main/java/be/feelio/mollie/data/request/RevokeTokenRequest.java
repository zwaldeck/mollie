package be.feelio.mollie.data.request;

import be.feelio.mollie.data.enums.GrantType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RevokeTokenRequest {

    @JsonProperty("token_type_hint")
    private GrantType tokenTypeHint;

    private String token;
}
