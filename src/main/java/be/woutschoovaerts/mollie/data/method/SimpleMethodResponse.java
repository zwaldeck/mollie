package be.woutschoovaerts.mollie.data.method;

import be.woutschoovaerts.mollie.data.common.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleMethodResponse {

    private String resource;
    private String id;
    private String name;
    private Image image;
}
