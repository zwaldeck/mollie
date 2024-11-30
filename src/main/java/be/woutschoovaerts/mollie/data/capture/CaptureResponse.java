package be.woutschoovaerts.mollie.data.capture;

import be.woutschoovaerts.mollie.data.common.Amount;
import be.woutschoovaerts.mollie.data.links.CaptureLinks;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CaptureResponse {

    private String resource;

    private String id;

    private String mode;

    private String description;

    @Builder.Default
    private Optional<Amount> amount = Optional.empty();

    @Builder.Default
    private Optional<Amount> settlementAmount = Optional.empty();

    private CaptureStatus status;

    private Map<String, Object> metadata;

    private String paymentId;

    @Builder.Default
    private Optional<String> shipmentId = Optional.empty();

    @Builder.Default
    private Optional<String> settlementId  = Optional.empty();

    private OffsetDateTime createdAt;

    @JsonProperty("_links")
    private CaptureLinks links;

    @JsonProperty("_embedded")
    @Builder.Default
    private Optional<CaptureEmbedded> embedded = Optional.empty();
}
