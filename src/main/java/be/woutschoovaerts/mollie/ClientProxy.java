package be.woutschoovaerts.mollie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientProxy {

    private String host;
    private int port;
    /**
     * Basic auth only works after setting: System.setProperty("jdk.http.auth.tunneling.disabledSchemes", "");
     */
    private String username;
    private String password;
}
