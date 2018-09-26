package be.feelio.mollie;

import be.feelio.mollie.handler.*;
import be.feelio.mollie.util.ObjectMapperService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.Unirest;
import lombok.Getter;

import java.io.IOException;

public class Client {

    @Getter
    private final String endpoint;

    @Getter
    private final String apiKey;

    public Client(String apiKey) {
        this.endpoint = "https://api.mollie.com/v2";
        this.apiKey = apiKey;

        initUniRest();
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

    private void initUniRest() {
        Unirest.setDefaultHeader("Authorization", "Bearer " + apiKey);
        Unirest.setDefaultHeader("Content-Type", "application/json");
        Unirest.setObjectMapper(new com.mashape.unirest.http.ObjectMapper() {

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
