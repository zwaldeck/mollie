package be.woutschoovaerts.mollie.data.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TerminalResponse {

    private String resource;

    private String id;

    private String description;

    private TerminalStatus status;

    private String brand;

    private String model;

    private String serialNumber;

    private String currency;

    @Builder.Default
    private Optional<String> profileId = Optional.empty();

    private OffsetDateTime createdAt;

    @JsonProperty("_links")
    private TerminalLinks links;
}
