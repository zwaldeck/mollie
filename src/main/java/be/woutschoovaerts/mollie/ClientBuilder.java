package be.woutschoovaerts.mollie;

public final class ClientBuilder {

    private String apiKey;
    private String organizationToken;
    private boolean testMode = false;
    private String userAgentString;
    private ClientProxy proxy;

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

    public ClientBuilder withUserAgent(String customUserAgentString) {
        this.userAgentString = customUserAgentString;
        return this;
    }

    public ClientBuilder withProxy(ClientProxy clientProxy) {
        this.proxy = clientProxy;
        return this;
    }

    public Client build() {
        Client client = new Client(apiKey, proxy);

        client.setAccessToken(organizationToken);

        if (testMode) {
            client.enableTestMode();
        }

        client.setUserAgentString(userAgentString);

        return client;
    }
}
