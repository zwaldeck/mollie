package be.woutschoovaerts.mollie.data.order;

import be.woutschoovaerts.mollie.data.common.AddressResponse;
import be.woutschoovaerts.mollie.data.common.Amount;
import be.woutschoovaerts.mollie.data.common.Locale;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class  OrderResponse {

    private String resource;

    private String id;

    private String mode;

    private String orderNumber;

    @Builder.Default
    private List<OrderLineResponse> lines = new ArrayList<>();

    private Amount amount;

    @Builder.Default
    private Optional<Amount> amountRefunded = Optional.empty();

    @Builder.Default
    private Optional<Amount> amountCaptured = Optional.empty();

    @Builder.Default
    private Optional<String> redirectUrl = Optional.empty();

    @Builder.Default
    private Optional<String> cancelUrl = Optional.empty();

    @Builder.Default
    private Optional<String> webhookUrl = Optional.empty();

    private AddressResponse billingAddress;

    private AddressResponse shippingAddress;

    private Locale locale;

    private String method;

    private boolean shopperCountryMustMatchBillingCountry;

    @Builder.Default
    private Map<String, Object> metadata = new HashMap<>();

    private OrderStatus status;

    private boolean isCancelable;

    private Map<String, Object> payment;

    private String profileId;

    private OffsetDateTime createdAt;

    @Builder.Default
    private Optional<OffsetDateTime> authorizedAt = Optional.empty();

    @Builder.Default
    private Optional<OffsetDateTime> paidAt  = Optional.empty();

    @Builder.Default
    private Optional<OffsetDateTime> canceledAt = Optional.empty();

    @Builder.Default
    private Optional<OffsetDateTime> expiresAt = Optional.empty();

    @Builder.Default
    private Optional<OffsetDateTime> expiredAt = Optional.empty();

    @Builder.Default
    private Optional<OffsetDateTime> completedAt  = Optional.empty();

    @JsonProperty("_links")
    private OrderLinks links;

    @Builder.Default
    @JsonProperty("_embedded")
    private Optional<OrderEmbedded> embedded  = Optional.empty();


}
