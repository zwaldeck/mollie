package be.woutschoovaerts.mollie.data.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QrCode {

    private int height;

    private int width;

    private String src;
}
