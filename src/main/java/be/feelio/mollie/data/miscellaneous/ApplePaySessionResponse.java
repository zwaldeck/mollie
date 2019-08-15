package be.feelio.mollie.data.miscellaneous;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplePaySessionResponse {

    private long epochTimestamp;

    private long expiresAt;

    private String merchantSessionIdentifier;

    private String nonce;

    private String merchantIdentifier;

    private String domainName;

    private String displayName;

    private String signature;
}
