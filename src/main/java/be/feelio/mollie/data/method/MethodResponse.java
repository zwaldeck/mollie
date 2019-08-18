package be.feelio.mollie.data.method;

import be.feelio.mollie.data.common.Amount;
import be.feelio.mollie.data.common.Image;
import be.feelio.mollie.data.links.MethodLinks;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MethodResponse {

    private String resource;
    private String id;
    private String description;
    private Amount minimumAmount;
    private Amount maximumAmount;
    private Image image;

    @JsonProperty("_links")
    private MethodLinks links;

    private List<SimpleMethodResponse> issuers;
    private List<MethodPricing> pricing;
}
