package be.feelio.mollie.handler;

import be.feelio.mollie.exception.MollieException;
import be.feelio.mollie.util.ObjectMapperService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public abstract class AbstractHandler {

    private Logger log = LoggerFactory.getLogger(AbstractHandler.class);

    protected final String baseUrl;
    protected final ObjectMapper mapper;

    public AbstractHandler(String baseUrl) {
        this.baseUrl = baseUrl;
        this.mapper = ObjectMapperService.getInstance().getMapper();
    }

    protected void validateResponse(HttpResponse<String> response) throws IOException, MollieException {
        if (response.getStatus() < 200 || response.getStatus() > 300) {
            log.error("Error response from mollie with status code '{}' and body: {}",
                    response.getStatus(), response.getBody());
            throw new MollieException("Error response from mollie", mapper.readValue(response.getBody(),
                    new TypeReference<Map>() {
                    }));
        }
    }
}
