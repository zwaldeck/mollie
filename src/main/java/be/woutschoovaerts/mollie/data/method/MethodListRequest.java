package be.woutschoovaerts.mollie.data.method;

import be.woutschoovaerts.mollie.data.common.Amount;
import be.woutschoovaerts.mollie.data.common.Locale;
import be.woutschoovaerts.mollie.data.order.OrderLineCategory;
import be.woutschoovaerts.mollie.data.payment.PaymentMethod;
import be.woutschoovaerts.mollie.data.payment.SequenceType;
import be.woutschoovaerts.mollie.util.QueryParamMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MethodListRequest implements QueryParamMapper {

    @Builder.Default
    private Optional<Amount> amount = Optional.empty();

    @Builder.Default
    private Optional<Locale> locale = Optional.empty();

    @Builder.Default
    private Optional<SequenceType> sequenceType = Optional.of(SequenceType.ONE_OFF);

    @Builder.Default
    private Optional<String> billingCountry = Optional.empty();

    @Builder.Default
    private Optional<OrderLineCategory> orderLineCategories = Optional.empty();

    @Builder.Default
    private Optional<MethodResource> resource = Optional.of(MethodResource.PAYMENTS);

    @Builder.Default
    private Optional<PaymentMethod> includeWallets = Optional.empty();

    @Builder.Default
    private List<MethodIncludes> include = new ArrayList<>();

    // OAuth params
    @Builder.Default
    private Optional<String> profileId = Optional.empty();

    @Builder.Default
    private Optional<Boolean> testmode = Optional.empty();
}
