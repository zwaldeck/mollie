package be.woutschoovaerts.mollie.data.permission;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionResponse {

    private String resource;

    private String id;

    private String description;

    private boolean granted;

    @JsonProperty("_links")
    private PermissionLinks links;

}
