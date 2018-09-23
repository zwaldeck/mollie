package be.feelio.mollie.handler;

import be.feelio.mollie.exception.MollieException;
import be.feelio.mollie.json.common.Pagination;
import be.feelio.mollie.json.response.MethodListResponse;
import be.feelio.mollie.json.response.MethodResponse;
import be.feelio.mollie.util.ObjectMapperService;
import be.feelio.mollie.util.QueryParams;
import com.fasterxml.jackson.core.type.TypeReference;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class MethodHandler extends AbstractHandler {

    private static final Logger log = LoggerFactory.getLogger(MethodHandler.class);

    public MethodHandler(String baseUrl) {
        super(baseUrl);
    }

    public Pagination<MethodListResponse> listMethods() throws MollieException {
        return listMethods(QueryParams.EMPTY);
    }

    public Pagination<MethodListResponse> listMethods(QueryParams params) throws MollieException {
        try {
            String url = baseUrl + "/methods" + params.toString();

            log.info("Executing 'GET {}'", url);
            HttpResponse<String> response = Unirest.get(url).asString();

            validateResponse(response);
            log.info("Successful response 'GET {}'", url);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<Pagination<MethodListResponse>>() {
                    });
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    public MethodResponse getMethod(String methodId) throws MollieException {
        return getMethod(methodId, QueryParams.EMPTY);
    }

    public MethodResponse getMethod(String methodId, QueryParams params) throws MollieException {
        try {
            String url = baseUrl + "/methods/" + methodId + params.toString();

            log.info("Executing 'GET {}'", url);
            HttpResponse<String> response = Unirest.get(url).asString();

            validateResponse(response);
            log.info("Successful response 'GET {}'", url);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<MethodResponse>() {
                    });
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }
}
