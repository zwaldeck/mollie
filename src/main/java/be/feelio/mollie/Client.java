package be.feelio.mollie;

import be.feelio.mollie.handler.*;
import be.feelio.mollie.util.Config;
import be.feelio.mollie.util.ObjectMapperService;
import com.fasterxml.jackson.core.JsonProcessingException;
import kong.unirest.ObjectMapper;
import kong.unirest.Unirest;
import lombok.Getter;

import java.io.IOException;

public class Client {

    @Getter
    private final String endpoint;


    public Client(String apiKey) {
        this.endpoint = "https://api.mollie.com/v2";

        // TODO: Check valid api key
        Config.getInstance().setApiKey(apiKey);
        Config.getInstance().setAccessToken(null);

        initUniRest();
    }

    /**
     * Set the access token, the requests will use the access token instead of the api key
     *
     * @param accessToken The access token (Generated or Organization token)
     */
    public void setAccessToken(String accessToken) {
        // TODO: Check valid access token
        Config.getInstance().setAccessToken(accessToken);
    }

    /**
     * Removes the access token, the requests will start using the api key again
     */
    public void revokeAccessToken() {
        Config.getInstance().setAccessToken(null);
    }

    /**
     * Handles connect actions
     *
     * @return ConnectHandler object
     */
    public ConnectHandler connect() {
        return new ConnectHandler();
    }

    /**
     * Handles payment actions
     *
     * @return PaymentHandler object
     */
    public PaymentHandler payments() {
        return new PaymentHandler(endpoint);
    }

    /**
     * Handles method actions
     *
     * @return MethodHandler object
     */
    public MethodHandler methods() {
        return new MethodHandler(endpoint);
    }

    /**
     * Handles refund actions
     *
     * @return RefundHandler object
     */
    public RefundHandler refunds() {
        return new RefundHandler(endpoint);
    }

    /**
     * Handles charge back actions
     *
     * @return ChargebackHandler object
     */
    public ChargebackHandler chargebacks() {
        return new ChargebackHandler(endpoint);
    }

    /**
     * Handles capture actions
     *
     * @return CaptureHandler object
     */
    public CaptureHandler captures() {
        return new CaptureHandler(endpoint);
    }

    /**
     * Handles order actions
     *
     * @return OrderHandler object
     */
    public OrderHandler orders() {
        return new OrderHandler(endpoint);
    }

    /**
     * Handles shipment actions
     *
     * @return ShipmentHandler object
     */
    public ShipmentHandler shipments() {
        return new ShipmentHandler(endpoint);
    }

    /**
     * Handles customer actions
     *
     * @return CustomerHandler object
     */
    public CustomerHandler customers() {
        return new CustomerHandler(endpoint);
    }

    /**
     * Handles mandate actions
     *
     * @return MandateHandler object
     */
    public MandateHandler mandates() {
        return new MandateHandler(endpoint);
    }

    /**
     * Handles subscription actions
     *
     * @return MethodHandler object
     */
    public SubscriptionHandler subscriptions() {
        return new SubscriptionHandler(endpoint);
    }

    /**
     * Handles permission actions
     *
     * @return PermissionHandler object
     */
    public PermissionHandler permissions() {
        return new PermissionHandler(endpoint);
    }

    /**
     * Handles organization actions
     *
     * @return OrganizationHandler object
     */
    public OrganizationHandler organizations() {
        return new OrganizationHandler(endpoint);
    }

    private void initUniRest() {
        Unirest.config()
            .setObjectMapper(new ObjectMapper() {

            @Override
            public <T> T readValue(String value, Class<T> type) {
                try {
                    return ObjectMapperService.getInstance().getMapper().readValue(value, type);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public String writeValue(Object value) {
                try {
                    return ObjectMapperService.getInstance().getMapper().writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
