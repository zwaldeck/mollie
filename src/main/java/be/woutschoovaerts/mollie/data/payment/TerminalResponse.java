package be.woutschoovaerts.mollie.data.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TerminalResponse {

    private String id;

    private String profileId;

    private String description;

    private TerminalStatus status;

    private String brand;

    private String model;

    private String serialNumber;

    private String currency;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

    @JsonProperty("_links")
    private TerminalLinks links;
}
