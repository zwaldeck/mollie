package be.woutschoovaerts.mollie.data.payment;

import be.woutschoovaerts.mollie.data.common.Amount;
import be.woutschoovaerts.mollie.data.common.LineCategory;
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
public class PaymentLineRequest {

    @Builder.Default
    private Optional<PaymentLineType> type = Optional.empty();

    private String description;

    private int quantity;

    @Builder.Default
    private Optional<String> quantityUnit = Optional.empty();

    private Amount unitPrice;

    @Builder.Default
    private Optional<Amount> discountAmount = Optional.empty();

    @Builder.Default
    private Optional<RecurringPaymentLineRequest> recurringPaymentLineRequest = Optional.empty();

    private Amount totalAmount;

    @Builder.Default
    private Optional<String> vatRate = Optional.empty();

    @Builder.Default
    private Optional<Amount> vatAmount = Optional.empty();

    @Builder.Default
    private Optional<String> sku = Optional.empty();

    @Builder.Default
    private Optional<String> imageUrl = Optional.empty();

    @Builder.Default
    private Optional<String> productUrl = Optional.empty();

    @Builder.Default
    private Optional<List<LineCategory>> categories = Optional.empty();


}
