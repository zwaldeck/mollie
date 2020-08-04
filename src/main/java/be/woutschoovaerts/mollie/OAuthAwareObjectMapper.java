package be.woutschoovaerts.mollie;

import be.woutschoovaerts.mollie.util.Config;
import be.woutschoovaerts.mollie.util.ObjectMapperService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import kong.unirest.ObjectMapper;

import java.io.IOException;

public class OAuthAwareObjectMapper implements ObjectMapper {

    private final Config config;

    public OAuthAwareObjectMapper(Config config) {
        this.config = config;
    }

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
            JsonNode node = ObjectMapperService.getInstance().getMapper().valueToTree(value);
            if (node.isObject() && config.shouldAddTestMode()) {
                ObjectNode object = (ObjectNode) node;
                object.put("testmode", true);
                return ObjectMapperService.getInstance().getMapper().writeValueAsString(object);
            } else {
                return ObjectMapperService.getInstance().getMapper().writeValueAsString(value);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
