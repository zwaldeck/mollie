package be.feelio.mollie.data.permission;

import be.feelio.mollie.data.response.PermissionResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionListEmbedded {

    private List<PermissionResponse> permissions;

}
