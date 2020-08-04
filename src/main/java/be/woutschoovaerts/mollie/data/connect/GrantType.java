package be.woutschoovaerts.mollie.data.connect;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum GrantType {

    @JsonProperty("authorization_code")
    AUTHORIZATION_CODE,

    @JsonProperty("refresh_token")
    REFRESH_TOKEN;
}
