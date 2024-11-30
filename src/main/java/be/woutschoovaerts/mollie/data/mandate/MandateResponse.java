package be.woutschoovaerts.mollie.data.mandate;

import be.woutschoovaerts.mollie.data.links.MandateLinks;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Optional;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MandateResponse {

    private String resource;

    private String id;

    private String mode;

    private MandatePaymentMethod method;

    private MandateDetailsResponse details;

    private LocalDate signatureDate;

    private String mandateReference;

    private MandateStatus status;

    @Builder.Default
    private Optional<String> customerId = Optional.empty();

    private OffsetDateTime createdAt;

    @JsonProperty("_links")
    private MandateLinks links;
}
