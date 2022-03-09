package be.woutschoovaerts.mollie.handler;

import be.woutschoovaerts.mollie.ClientProxy;
import be.woutschoovaerts.mollie.exception.MollieException;
import be.woutschoovaerts.mollie.util.Config;
import be.woutschoovaerts.mollie.util.ObjectMapperService;
import be.woutschoovaerts.mollie.util.QueryParamMapper;
import be.woutschoovaerts.mollie.util.QueryParams;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;

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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.Executors;

import static java.lang.String.format;

public abstract class AbstractHandler {

    private final Logger log;
    private final String baseUrl;
    private final ObjectMapper mapper;
    private final HttpClient httpClient;
    protected final Config config;
    private final Map<String, String> headers;
    private final ObjectWriter objectWriter;

    AbstractHandler(String baseUrl, Logger log, Config config) {
        this.baseUrl = baseUrl;
        this.log = log;
        this.mapper = ObjectMapperService.getInstance().getMapper();
        this.objectWriter = mapper.writerWithDefaultPrettyPrinter();
        this.config = config;
        this.httpClient = httpClient();
        this.headers = configureAllHeaders();
    }

    protected <T> T get(String uri, QueryParams params, TypeReference<T> clazz) throws MollieException {
        final HttpRequest httpRequest = buildHttpRequest(uri, params);

        try {
            HttpResponse<String> response = httpClient
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());
            validateResponse(response);

            return mapper.readValue(response.body(), clazz);
        } catch (IOException | InterruptedException e) {
            throw new MollieException(e);
        }
    }

    protected <T> T get(String uri, QueryParamMapper params, TypeReference<T> clazz) throws MollieException {
        final HttpRequest httpRequest = buildHttpRequest(uri, params);

        try {
            HttpResponse<String> response = httpClient
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());
            validateResponse(response);

            return mapper.readValue(response.body(), clazz);
        } catch (IOException | InterruptedException e) {
            throw new MollieException(e);
        }
    }

    protected <T> CompletableFuture<T> getASync(String uri, QueryParams params, TypeReference<T> clazz) {
        final HttpRequest httpRequest = buildHttpRequest(uri, params);

        return httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(this::validateASyncResponse)
                .thenApplyAsync(stringHttpResponse -> readResponse(stringHttpResponse, clazz));
    }

    protected <T> CompletableFuture<T> getASync(String uri, QueryParamMapper params, TypeReference<T> clazz) {
        final HttpRequest httpRequest = buildHttpRequest(uri, params);

        return httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(this::validateASyncResponse)
                .thenApplyAsync(stringHttpResponse -> readResponse(stringHttpResponse, clazz));
    }

    protected <T> T postWithoutBody(String uri, QueryParams params, TypeReference<T> clazz) throws MollieException {
        URI url = URI.create(baseUrl + uri + params.toString());

        log.debug("Executing 'POST {}'", url);

        final HttpRequest httpRequest = httpRequestBuilder(url)
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            HttpResponse<String> response = httpClient
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());

            validateResponse(response);

            return mapper.readValue(response.body(), clazz);
        } catch (IOException | InterruptedException e) {
            throw new MollieException(e);
        }
    }

    protected <T> T post(String uri, Object body, QueryParams params, TypeReference<T> clazz) throws MollieException {
        return getHttpResponse(uri, body, params, "POST", clazz);
    }

    protected <T> T post(URI url, String clientId, String clientSecret, String body, TypeReference<T> clazz)
            throws MollieException {
        return getHttpResponse(url, clientId, clientSecret, body, "POST", clazz);
    }

    protected <T> T patch(String uri, Object body, QueryParams params, TypeReference<T> clazz) throws MollieException {
        return getHttpResponse(uri, body, params, "PATCH", clazz);
    }

    protected <T> T delete(String uri, QueryParams params, TypeReference<T> clazz) throws MollieException {
        Map<String, Object> body = new HashMap<>();
        if (config.shouldAddTestMode()) {
            body.put("testmode", "true");
        }

        return getHttpResponse(uri, body, params, "DELETE", clazz);
    }

    protected <T> T delete(String uri, Object body, QueryParams params, TypeReference<T> clazz) throws MollieException {
        return getHttpResponse(uri, body, params, "DELETE", clazz);
    }

    protected <T> T delete(URI url, String clientId, String clientSecret, String body, TypeReference<T> clazz)
            throws MollieException {
        return getHttpResponse(url, clientId, clientSecret, body, "DELETE", clazz);
    }

    private <T> T readResponse(HttpResponse<String> stringHttpResponse, TypeReference<T> clazz) {
        try {
            log.debug("Successful response '{} {}'", stringHttpResponse.request().method(), stringHttpResponse.uri());
            return mapper.readValue(stringHttpResponse.body(), clazz);
        } catch (JsonProcessingException e) {
            throw new CompletionException(e);
        }
    }

    private <T> T getHttpResponse(String uri, Object body, QueryParams params, String method, TypeReference<T> clazz)
            throws MollieException {
        URI url = URI.create(baseUrl + uri + params.toString());

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

            if (response.statusCode() == 204) {
                return null;
            } else {
                return mapper.readValue(response.body(), clazz);
            }
        } catch (IOException | InterruptedException e) {
            throw new MollieException(e);
        }
    }

    private <T> T getHttpResponse(URI url, String clientId, String clientSecret, String body, String method,
                                  TypeReference<T> clazz) throws MollieException {
        log.debug("Executing '{} {}'", method, url);

        final HttpRequest httpRequest = buildHttpRequest(url, clientId, clientSecret, body, method);

        try {
            HttpResponse<String> response = httpClient
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());

            validateResponse(response);

            if (response.statusCode() == 204) {
                return null;
            } else {
                return mapper.readValue(response.body(), clazz);
            }
        } catch (IOException | InterruptedException e) {
            throw new MollieException(e);
        }
    }

    protected void validateResponse(HttpResponse<String> response) throws MollieException, JsonProcessingException {
        if (response.statusCode() < 200 || response.statusCode() > 300) {
            log.error("Error response from mollie with status code '{}' and body: {}",
                    response.statusCode(), response.body());
            throw new MollieException("Error response from mollie", mapper.readValue(response.body(),
                    new TypeReference<>() {
                    }));
        } else {
            log.debug("Successful response '{} {}'", response.request().method(), response.uri());
        }
    }

    protected HttpResponse<String> validateASyncResponse(HttpResponse<String> response) {
        if (response.statusCode() < 200 || response.statusCode() > 300) {
            log.error("Error response from mollie with status code '{}' and body: {}",
                    response.statusCode(), response.body());
            try {
                throw new CompletionException("Error response from mollie", mapper.readValue(response.body(),
                        new TypeReference<>() {
                        }));
            } catch (JsonProcessingException e) {
                throw new CompletionException("Error parsing response from mollie", e);
            }
        }
        return response;
    }

    private Map<String, String> configureAllHeaders() {
        Map<String, String> map = new HashMap<>();

        map.put("Content-Type", "application/json");
        map.put("Authorization", "Bearer " + config.getBearerToken());
        config.getProxy()
                .filter(proxy -> Objects.nonNull(proxy.getUsername()) && Objects.nonNull(proxy.getPassword()))
                .ifPresent(proxy -> map.put("Proxy-Authorization", getBasicAuth(proxy)));
        config.getUserAgentString().ifPresent(userAgentString -> map.put("User-Agent", userAgentString));

        return map;
    }

    private HttpRequest buildHttpRequest(String uri, QueryParams params) {
        if (config.shouldAddTestMode() && !params.containsKey("testmode")) {
            params.put("testmode", "true");
        }

        URI url = URI.create(baseUrl + uri + params.toString());

        log.debug("Executing 'GET {}'", url);

        return httpRequestBuilder(url)
                .GET()
                .build();
    }

    private HttpRequest buildHttpRequest(String uri, QueryParamMapper params) {
        URI url = URI.create(baseUrl + uri + params.toQueryParams());

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

        config.getHttpThreadPoolSize().ifPresent(httpThreadPoolSize ->
                builder.executor(Executors.newFixedThreadPool(httpThreadPoolSize)));

        return builder.build();
    }
}
