package be.woutschoovaerts.mollie;

import be.woutschoovaerts.mollie.util.ObjectMapperService;
import com.fasterxml.jackson.core.JsonProcessingException;
import kong.unirest.ObjectMapper;

import java.io.IOException;

public class JacksonObjectMapper implements ObjectMapper {

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
}
