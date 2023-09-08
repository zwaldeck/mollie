package be.woutschoovaerts.mollie.data.organization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAgentTokenResponse {

    private String token;

    private LocalDate startsAt;

    private LocalDate endsAt;

}
