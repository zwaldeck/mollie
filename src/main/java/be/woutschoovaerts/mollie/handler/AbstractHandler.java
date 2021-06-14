package be.woutschoovaerts.mollie.handler;

import be.woutschoovaerts.mollie.exception.MollieException;
import be.woutschoovaerts.mollie.util.Config;
import be.woutschoovaerts.mollie.util.ObjectMapperService;
import be.woutschoovaerts.mollie.util.QueryParams;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractHandler {

    private final Logger log;
    private final String baseUrl;
    private final ObjectMapper mapper;
    protected final Config config;

    AbstractHandler(String baseUrl, Logger log, Config config) {
        this.baseUrl = baseUrl;
        this.log = log;
        this.mapper = ObjectMapperService.getInstance().getMapper();
        this.config = config;
    }

    protected HttpResponse<String> get(String uri) throws IOException, MollieException {
        return get(uri, QueryParams.EMPTY);
    }

    protected HttpResponse<String> get(String uri, QueryParams params) throws IOException, MollieException {
        if (config.shouldAddTestMode() && !params.containsKey("testmode")) {
            params.put("testmode", "true");
        }

        String url = baseUrl + uri + params.toString();

        log.info("Executing 'GET {}'", url);

        HttpResponse<String> response = Unirest
                .get(url)
                .headers(getHeaderMap())
                .asString();

        validateResponse(response);
        log.info("Successful response 'GET {}'", url);

        return response;
    }

    protected HttpResponse<String> postWithoutBody(String uri, QueryParams params)
            throws IOException, MollieException {
        String url = baseUrl + uri + params.toString();

        log.info("Executing 'POST {}'", url);

        HttpResponse<String> response = Unirest
                .post(url)
                .headers(getHeaderMap())
                .asString();

        validateResponse(response);
        log.info("Successful response 'POST {}'", url);

        return response;
    }

    protected HttpResponse<String> post(String uri, Object body) throws IOException, MollieException {
        return post(uri, body, QueryParams.EMPTY);
    }

    protected HttpResponse<String> post(String uri, Object body, QueryParams params)
            throws IOException, MollieException {
        String url = baseUrl + uri + params.toString();

        log.info("Executing 'POST {}'", url);

        HttpResponse<String> response = Unirest
                .post(url)
                .headers(getHeaderMap())
                .body(body)
                .asString();

        validateResponse(response);
        log.info("Successful response 'POST {}'", url);

        return response;
    }

    protected HttpResponse<String> patch(String uri, Object body) throws IOException, MollieException {
        return patch(uri, body, QueryParams.EMPTY);
    }

    protected HttpResponse<String> patch(String uri, Object body, QueryParams params)
            throws IOException, MollieException {
        String url = baseUrl + uri + params.toString();

        log.info("Executing 'PATCH {}'", url);

        HttpResponse<String> response = Unirest
                .patch(url)
                .headers(getHeaderMap())
                .body(body)
                .asString();

        validateResponse(response);
        log.info("Successful response 'PATCH {}'", url);

        return response;
    }

    protected HttpResponse<String> delete(String uri) throws IOException, MollieException {
        return delete(uri, QueryParams.EMPTY);
    }

    protected HttpResponse<String> delete(String uri, QueryParams params) throws IOException, MollieException {
        Map<String, Object> body = new HashMap<>();
        if (config.shouldAddTestMode()) {
            body.put("testmode", "true");
        }

        String url = baseUrl + uri + params.toString();

        log.info("Executing 'DELETE {}'", url);

        HttpResponse<String> response = Unirest
                .delete(url)
                .headers(getHeaderMap())
                .body(body)
                .asString();

        validateResponse(response);
        log.info("Successful response 'DELETE {}'", url);

        return response;
    }

    protected HttpResponse<String> delete(String uri, Object body, QueryParams params)
            throws IOException, MollieException {
        String url = baseUrl + uri + params.toString();

        log.info("Executing 'DELETE {}'", url);

        HttpResponse<String> response = Unirest
                .delete(url)
                .headers(getHeaderMap())
                .body(body)
                .asString();

        validateResponse(response);
        log.info("Successful response 'DELETE {}'", url);

        return response;
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

    private Map<String, String> getHeaderMap() {
        Map<String, String> map = new HashMap<>();

        map.put("Content-Type", "application/json");
        map.put("Authorization", "Bearer " + config.getBearerToken());

        config.getUserAgent().ifPresent(userAgent -> map.put("User-Agent", userAgent));

        return map;
    }
}
