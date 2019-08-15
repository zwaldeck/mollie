package be.feelio.mollie;

public final class ClientBuilder {

    private String apiKey;
    private String organizationToken;
    private boolean testMode = false;

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

        return client;
    }
}
