package be.woutschoovaerts.mollie.util;

import be.woutschoovaerts.mollie.exception.MollieException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class RestService {

    private static final String BASE_URL = "https://api.mollie.com/v2";

    private final ObjectMapper mapper;
    private final Config config;

    public RestService(Config config) {
        mapper = ObjectMapperService.getInstance().getMapper();
        this.config = config;
    }

    public <T> T get(String uri, TypeReference<T> typeRef) throws IOException, MollieException {
        return get(uri, new QueryParams(), typeRef);
    }

    public <T> T get(String uri, QueryParams params, TypeReference<T> typeRef) throws IOException, MollieException {
        return get(uri, params, true, typeRef);
    }

    public <T> T get(String uri, QueryParams params, boolean allowsTestMode, TypeReference<T> typeRef)
            throws IOException, MollieException {
        if (allowsTestMode && config.shouldAddTestMode() && !params.containsKey("testmode")) {
            params.put("testmode", "true");
        }

        String url = BASE_URL + uri + params.toString();

        log.info("Executing 'GET {}'", url);

        HttpResponse<String> response = Unirest
                .get(url)
                .headers(configureAllHeaders())
                .asString();

        validateResponse(response);
        log.info("Successful response 'GET {}'", url);

        return convertResponseBodyToObject(response.getBody(), typeRef);

    }

    public <T> T postWithoutBody(String uri, QueryParams params, TypeReference<T> typeRef)
            throws IOException, MollieException {
        String url = BASE_URL + uri + params.toString();

        log.info("Executing 'POST {}'", url);

        HttpResponse<String> response = Unirest
                .post(url)
                .headers(configureAllHeaders())
                .asString();

        validateResponse(response);
        log.info("Successful response 'POST {}'", url);

        return convertResponseBodyToObject(response.getBody(), typeRef);
    }

    public <T> T post(String uri, Object body, TypeReference<T> typeRef) throws IOException, MollieException {
        return post(uri, body, new QueryParams(), typeRef);
    }

    public <T> T post(String uri, Object body, QueryParams params, TypeReference<T> typeRef)
            throws IOException, MollieException {
        String url = BASE_URL + uri + params.toString();

        log.info("Executing 'POST {}'", url);

        HttpResponse<String> response = Unirest
                .post(url)
                .headers(configureAllHeaders())
                .body(body)
                .asString();

        validateResponse(response);
        log.info("Successful response 'POST {}'", url);

        return convertResponseBodyToObject(response.getBody(), typeRef);
    }

    public <T> T patch(String uri, Object body, TypeReference<T> typeRef) throws IOException, MollieException {
        return patch(uri, body, new QueryParams(), typeRef);
    }

    public <T> T patch(String uri, Object body, QueryParams params, TypeReference<T> typeRef)
            throws IOException, MollieException {
        String url = BASE_URL + uri + params.toString();

        log.info("Executing 'PATCH {}'", url);

        HttpResponse<String> response = Unirest
                .patch(url)
                .headers(configureAllHeaders())
                .body(body)
                .asString();

        validateResponse(response);
        log.info("Successful response 'PATCH {}'", url);

        return convertResponseBodyToObject(response.getBody(), typeRef);
    }

    public <T> T delete(String uri, TypeReference<T> typeRef) throws IOException, MollieException {
        return delete(uri, new QueryParams(), typeRef);
    }

    public <T> T delete(String uri, QueryParams params, TypeReference<T> typeRef) throws IOException, MollieException {
        return delete(uri, params, true, typeRef);
    }

    public <T> T delete(String uri, QueryParams params, boolean allowsTestMode, TypeReference<T> typeRef)
            throws IOException, MollieException {
        Map<String, Object> body = new HashMap<>();
        if (allowsTestMode && config.shouldAddTestMode()) {
            body.put("testmode", true);
        }

        String url = BASE_URL + uri + params.toString();

        log.info("Executing 'DELETE {}'", url);

        HttpResponse<String> response = Unirest
                .delete(url)
                .headers(configureAllHeaders())
                .body(body)
                .asString();

        validateResponse(response);
        log.info("Successful response 'DELETE {}'", url);

        return convertResponseBodyToObject(response.getBody(), typeRef);
    }

    public <T> T delete(String uri, Object body, QueryParams params, TypeReference<T> typeRef)
            throws IOException, MollieException {
        String url = BASE_URL + uri + params.toString();

        log.info("Executing 'DELETE {}'", url);

        HttpResponse<String> response = Unirest
                .delete(url)
                .headers(configureAllHeaders())
                .body(body)
                .asString();

        validateResponse(response);
        log.info("Successful response 'DELETE {}'", url);

        return convertResponseBodyToObject(response.getBody(), typeRef);
    }

    public void validateResponse(HttpResponse<String> response) throws IOException, MollieException {
        if (response.getStatus() < 200 || response.getStatus() > 300) {
            log.error("Error response from mollie with status code '{}' and body: {}",
                    response.getStatus(), response.getBody());
            throw new MollieException("Error response from mollie", mapper.readValue(response.getBody(),
                    new TypeReference<Map>() {
                    }));
        }
    }

    private Map<String, String> configureAllHeaders() {
        Map<String, String> map = new HashMap<>();

        map.put("Content-Type", "application/json");
        map.put("Authorization", "Bearer " + config.getBearerToken());

        config.getUserAgentString().ifPresent(userAgentString -> map.put("User-Agent", userAgentString));
        config.getIdempotencyKey().ifPresent(key -> map.put("Idempotency-Key", key));

        return map;
    }

    private <T> T convertResponseBodyToObject(String body, TypeReference<T> typeRef) throws IOException {
        if (typeRef.getType().equals(Void.TYPE) || typeRef.getType().equals(Void.class)) {
            return null;
        }

        return ObjectMapperService.getInstance().getMapper().readValue(body, typeRef);
    }
}
