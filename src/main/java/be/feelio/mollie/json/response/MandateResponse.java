package be.feelio.mollie.json.response;

import be.feelio.mollie.json.links.MandateLinks;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MandateResponse {

    private String resource;

    private String id;

    private String status;

    private MandateDetailsResponse details;

    private String mandateReference;

    private Date signatureDate;

    private Date createdAt;

    @JsonProperty("_links")
    private MandateLinks links;
}
