package be.woutschoovaerts.mollie.data.connect;

import be.woutschoovaerts.mollie.data.common.Locale;
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
public class AuthorizeRequest {

    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("redirect_uri")
    @Builder.Default
    private Optional<String> redirectUri = Optional.empty();

    private String state;

    private String[] scope;

    @JsonProperty("response_type")
    private ResponseType responseType;

    @JsonProperty("approval_prompt")
    private ApprovalPrompt approvalPrompt;

    private Locale locale;

    @JsonProperty("landing_page")
    @Builder.Default
    private LandingPageType landingPage = LandingPageType.LOGIN;
}
