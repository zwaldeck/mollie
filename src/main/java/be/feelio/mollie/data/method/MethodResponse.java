package be.feelio.mollie.data.method;

import be.feelio.mollie.data.common.Image;
import be.feelio.mollie.data.links.MethodLinks;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MethodResponse {

    private String resource;
    private String id;
    private String description;
    private Image image;

    @JsonProperty("_links")
    private MethodLinks links;

    private Optional<List<MethodResponse>> issuers;
}
