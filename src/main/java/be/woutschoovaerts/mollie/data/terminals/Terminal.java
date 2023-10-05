package be.woutschoovaerts.mollie.data.terminals;

import be.woutschoovaerts.mollie.data.payment.PaymentLinks;
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
public class Terminal {

    private String id;

    private String profileId;

    private String description;

    private String status;

    private String brand;

    private String model;

    @JsonProperty("serialNumber")
    private String serialNumber;

    private String currency;

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    @JsonProperty("_links")
    private TerminalLinks links;


}
