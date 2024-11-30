package be.woutschoovaerts.mollie.data.method;

import be.woutschoovaerts.mollie.data.common.Amount;
import be.woutschoovaerts.mollie.data.common.Image;
import be.woutschoovaerts.mollie.data.links.MethodLinks;
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
    private MethodStatus status;
    private List<MethodPricing> pricing;
    private List<SimpleMethodResponse> issuers;

    @JsonProperty("_links")
    private MethodLinks links;
}
