package be.feelio.mollie.data.response;

import be.feelio.mollie.data.common.Amount;
import be.feelio.mollie.data.enums.Locale;
import be.feelio.mollie.data.enums.OrderStatus;
import be.feelio.mollie.data.order.OrderAddressResponse;
import be.feelio.mollie.data.order.OrderEmbedded;
import be.feelio.mollie.data.order.OrderLineResponse;
import be.feelio.mollie.data.order.OrderLinks;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {

    private String resource;

    private String id;

    private String profileId;

    private String method;

    private String mode;

    private Amount amount;

    @Builder.Default
    private Optional<Amount> amountCaptured = Optional.empty();

    @Builder.Default
    private Optional<Amount> amountRefunded = Optional.empty();

    private OrderStatus status;

    private boolean isCancelable;

    private OrderAddressResponse billingAddress;

    @Builder.Default
    private Optional<Date> consumerDateOfBirth = Optional.empty();

    private String orderNumber;

    private OrderAddressResponse shippingAddress;

    private Locale locale;

    @Builder.Default
    private Map<String, Object> metadata = new HashMap<>();

    private String redirectUrl;

    @Builder.Default
    private List<OrderLineResponse> lines = new ArrayList<>();

    @Builder.Default
    private Optional<String> webhookUrl = Optional.empty();

    private Date createdAt;

    @Builder.Default
    private Optional<Date> expiresAt = Optional.empty();

    @Builder.Default
    private Optional<Date> expiredAt  = Optional.empty();

    @Builder.Default
    private Optional<Date> paidAt  = Optional.empty();

    @Builder.Default
    private Optional<Date> authorizedAt  = Optional.empty();

    @Builder.Default
    private Optional<Date> canceledAt  = Optional.empty();

    @Builder.Default
    private Optional<Date> completedAt  = Optional.empty();

    @Builder.Default
    @JsonProperty("_embedded")
    private Optional<OrderEmbedded> embedded  = Optional.empty();

    @JsonProperty("_links")
    private OrderLinks links;
}
