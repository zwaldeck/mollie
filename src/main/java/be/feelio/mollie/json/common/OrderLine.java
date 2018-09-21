package be.feelio.mollie.json.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderLine {

    private Optional<String> type;

    private String name;

    private String quantity;

    private Amount unitPrice;

    private Optional<Amount> discountAmount;

    private Amount totalAmount;

    private String vatRate;

    private Amount vatAmount;

    private Optional<String> sku;

    private Optional<String> imageUrl;

    private Optional<String> productUrl;
}
