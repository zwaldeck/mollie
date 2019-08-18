package be.feelio.mollie.data.connect;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("expires_in")
    private int expiresIn;

    @JsonProperty("token_type")
    private TokenType tokenType;

    @JsonIgnore
    private String[] scope;

    @JsonProperty("scope")
    private void setScope(String scopeString) {
        scope = scopeString.split(" ");
    }
}
