package be.feelio.mollie;

import be.feelio.mollie.handler.MethodHandler;
import be.feelio.mollie.handler.PaymentHandler;
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

    public Client(String version, String apiKey) {
        this.endpoint = "https://api.mollie.com/" + version;
        this.apiKey = apiKey;

        initUniRest();
    }

    public PaymentHandler payments() {
        return new PaymentHandler(endpoint);
    }

    public MethodHandler methods() {
        return new MethodHandler(endpoint);
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
