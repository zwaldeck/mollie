package be.feelio.mollie;

public final class ClientBuilder {

    private String version = "v2";
    private String apiKey;

    public ClientBuilder withVersion(String apiVersion) {
        this.version = apiVersion;
        return this;
    }

    public ClientBuilder withApiKey(String key) {
        this.apiKey = key;
        return this;
    }

    public Client build() {
        if (apiKey == null) {
            throw new IllegalArgumentException("API key not set. Please use withApiKey(key)");
        }

        return new Client(version, apiKey);
    }
}
