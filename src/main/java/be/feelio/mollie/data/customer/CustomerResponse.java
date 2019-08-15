package be.feelio.mollie.data.customer;

import be.feelio.mollie.data.links.CustomerLinks;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {

    private String resource;

    private String id;

    private String mode;

    private String name;

    private String email;

    private String locale;

    private Map<String, Object> metadata;

    private Date createdAt;

    @JsonProperty("_links")
    private CustomerLinks links;
}
