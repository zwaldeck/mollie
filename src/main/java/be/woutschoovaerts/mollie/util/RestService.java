package be.woutschoovaerts.mollie.util;

import be.woutschoovaerts.mollie.ClientProxy;
import be.woutschoovaerts.mollie.exception.MollieException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.lang.String.format;

@Slf4j
public class RestService {

    private static final String BASE_URL = "https://api.mollie.com/v2";

    private final ObjectMapper mapper;
    private final ObjectWriter objectWriter;
    private final Config config;
    private final Map<String, String> headers;
    private final HttpClient httpClient;

    public RestService(Config config) {
        this.mapper = ObjectMapperService.getInstance().getMapper();
        this.objectWriter = mapper.writerWithDefaultPrettyPrinter();
        this.config = config;
        this.headers = configureAllHeaders();
        this.httpClient = httpClient();
    }

    public <T> T get(String uri, TypeReference<T> typeRef) throws MollieException {
        return get(uri, new QueryParams(), typeRef);
    }

    public <T> T get(String uri, QueryParams params, TypeReference<T> typeRef) throws MollieException {
        return get(uri, params, true, typeRef);
    }

    public <T> T get(String uri, QueryParams params, boolean allowsTestMode, TypeReference<T> typeRef)
            throws MollieException {
        final HttpRequest httpRequest = buildHttpRequest(uri, params, allowsTestMode);

        try {
            HttpResponse<String> response = httpClient
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());

            validateResponse(response);

            return convertResponseBodyToObject(response.body(), typeRef);
        } catch (IOException | InterruptedException e) {
            log.error("An unexpected exception occurred", e);
            throw new MollieException(e);
        }
    }

    public <T> T postWithoutBody(String uri, QueryParams params, TypeReference<T> typeRef)
            throws MollieException {
        URI url = URI.create(BASE_URL + uri + params.toString());

        log.debug("Executing 'POST {}'", url);

        final HttpRequest httpRequest = httpRequestBuilder(url)
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            HttpResponse<String> response = httpClient
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());

            validateResponse(response);

            return convertResponseBodyToObject(response.body(), typeRef);
        } catch (IOException | InterruptedException e) {
            log.error("An unexpected exception occurred", e);
            throw new MollieException(e);
        }
    }

    public <T> T post(String uri, Object body, TypeReference<T> typeRef) throws MollieException {
        return post(uri, body, new QueryParams(), typeRef);
    }

    public <T> T post(String uri, Object body, QueryParams params, TypeReference<T> typeRef)
            throws MollieException {
        return getHttpResponse(uri, body, params, "POST", typeRef);
    }

    public <T> T post(URI url, String clientId, String clientSecret, String body, TypeReference<T> typeRef)
            throws MollieException {
        return getHttpResponse(url, clientId, clientSecret, body, "POST", typeRef);
    }

    public <T> T patch(String uri, Object body, TypeReference<T> typeRef) throws MollieException {
        return patch(uri, body, new QueryParams(), typeRef);
    }

    public <T> T patch(String uri, Object body, QueryParams params, TypeReference<T> typeRef)
            throws MollieException {
        return getHttpResponse(uri, body, params, "PATCH", typeRef);
    }

    public <T> T delete(String uri, TypeReference<T> typeRef) throws MollieException {
        return delete(uri, new QueryParams(), typeRef);
    }

    public <T> T delete(String uri, QueryParams params, TypeReference<T> typeRef) throws MollieException {
        return delete(uri, params, true, typeRef);
    }

    public <T> T delete(String uri, QueryParams params, boolean allowsTestMode, TypeReference<T> typeRef)
            throws MollieException {
        Map<String, Object> body = new HashMap<>();
        if (allowsTestMode && config.shouldAddTestMode()) {
            body.put("testmode", true);
        }

        return delete(uri, body, params, typeRef);
    }

    public <T> T delete(String uri, Object body, QueryParams params, TypeReference<T> typeRef)
            throws MollieException {
        return getHttpResponse(uri, body, params, "DELETE", typeRef);
    }

    public <T> T delete(URI url, String clientId, String clientSecret, String body, TypeReference<T> typeRef)
            throws MollieException {
        return getHttpResponse(url, clientId, clientSecret, body, "DELETE", typeRef);
    }

    private <T> T getHttpResponse(String uri, Object body, QueryParams params, String method, TypeReference<T> typeRef)
            throws MollieException {
        URI url = URI.create(BASE_URL + uri + params.toString());

        log.debug("Executing '{} {}'", method, url);

        try {
            String requestBody = objectWriter
                    .writeValueAsString(body);

            final HttpRequest httpRequest = httpRequestBuilder(url)
                    .method(method, HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = httpClient
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());

            validateResponse(response);

            return convertResponseBodyToObject(response.body(), typeRef);
        } catch (IOException | InterruptedException e) {
            log.error("An unexpected exception occurred", e);
            throw new MollieException(e);
        }
    }
    private <T> T getHttpResponse(URI url, String clientId, String clientSecret, String body, String method,
                                  TypeReference<T> typeRef) throws MollieException {
        log.debug("Executing '{} {}'", method, url);

        final HttpRequest httpRequest = buildHttpRequest(url, clientId, clientSecret, body, method);

        try {
            HttpResponse<String> response = httpClient
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());

            validateResponse(response);

            return convertResponseBodyToObject(response.body(), typeRef);
        } catch (IOException | InterruptedException e) {
            log.error("An unexpected exception occurred", e);
            throw new MollieException(e);
        }
    }

    private void validateResponse(HttpResponse<String> response) throws IOException, MollieException {
        if (response.statusCode() < 200 || response.statusCode() > 300) {
            log.error("Error response from mollie with status code '{}' and body: {}",
                    response.statusCode(), response.body());
            throw new MollieException("Error response from mollie", mapper.readValue(response.body(),
                    new TypeReference<Map>() {
                    }));
        }
    }

    private Map<String, String> configureAllHeaders() {
        Map<String, String> map = new HashMap<>();

        map.put("Content-Type", "application/json");
        map.put("Authorization", "Bearer " + config.getBearerToken());

        config.getProxy()
                .filter(proxy -> Objects.nonNull(proxy.getUsername()) && Objects.nonNull(proxy.getPassword()))
                .ifPresent(proxy -> map.put("Proxy-Authorization", getBasicAuth(proxy)));
        config.getUserAgentString().ifPresent(userAgentString -> map.put("User-Agent", userAgentString));
        config.getIdempotencyKey().ifPresent(key -> map.put("Idempotency-Key", key));

        return map;
    }

    private <T> T convertResponseBodyToObject(String body, TypeReference<T> typeRef) throws IOException {
        if (typeRef.getType().equals(Void.TYPE) || typeRef.getType().equals(Void.class)) {
            return null;
        }

        return mapper.readValue(body, typeRef);
    }

    private HttpRequest buildHttpRequest(String uri, QueryParams params, boolean allowsTestMode) {
        if (allowsTestMode && config.shouldAddTestMode() && !params.containsKey("testmode")) {
            params.put("testmode", "true");
        }

        URI url = URI.create(BASE_URL + uri + params.toString());

        log.debug("Executing 'GET {}'", url);

        return httpRequestBuilder(url)
                .GET()
                .build();
    }

    private HttpRequest buildHttpRequest(URI url, String clientId, String clientSecret, String body, String method) {
        return HttpRequest.newBuilder()
                .uri(url)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", getBasicAuth(clientId, clientSecret))
                .method(method, HttpRequest.BodyPublishers.ofString(body))
                .build();
    }

    private String getBasicAuth(ClientProxy proxy) {
        return getBasicAuth(proxy.getUsername(), proxy.getPassword());
    }

    private String getBasicAuth(String clientId, String clientSecret) {
        return "Basic " + new String(Base64.getEncoder().encode(format("%s:%s", clientId, clientSecret).getBytes()));
    }

    private HttpRequest.Builder httpRequestBuilder(URI url) {
        final HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(url);
        headers.forEach(builder::header);
        return builder;
    }

    private HttpClient httpClient() {
        final HttpClient.Builder builder = HttpClient.newBuilder();

        config.getProxy().ifPresent(proxy ->
                builder.proxy(ProxySelector.of(new InetSocketAddress(proxy.getHost(), proxy.getPort()))));

        return builder.build();
    }
}
