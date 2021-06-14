package be.woutschoovaerts.mollie;

public final class ClientBuilder {

    private String apiKey;
    private String organizationToken;
    private boolean testMode = false;
    private String userAgent;

    public ClientBuilder withApiKey(String key) {
        this.apiKey = key;
        return this;
    }

    public ClientBuilder withOrganizationToken(String token) {
        this.organizationToken = token;
        return this;
    }

    public ClientBuilder withTestMode(boolean shouldBeTestMode) {
        this.testMode = shouldBeTestMode;
        return this;
    }

    public ClientBuilder withUserAgent(String customUserAgent) {
        this.userAgent = customUserAgent;
        return this;
    }

    public Client build() {
        if (apiKey == null) {
            throw new IllegalArgumentException("API key not set. Please use withApiKey(key)");
        }

        Client client = new Client(apiKey);

        if (organizationToken != null) {
            client.setAccessToken(organizationToken);
        }

        if (testMode) {
            client.enableTestMode();
        }

        if (userAgent != null) {
            client.setUserAgent(userAgent);
        }

        return client;
    }
}
