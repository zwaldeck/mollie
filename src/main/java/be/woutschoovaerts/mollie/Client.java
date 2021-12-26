package be.woutschoovaerts.mollie;

import be.woutschoovaerts.mollie.handler.*;
import be.woutschoovaerts.mollie.util.Config;
import kong.unirest.Unirest;
import lombok.Getter;

public class Client {

    @Getter
    private final String endpoint;

    @Getter
    private final Config config;

    public Client(String apiKey) {
        this(apiKey, null);
    }

    public Client(String apiKey, ClientProxy proxy) {
        this.endpoint = "https://api.mollie.com/v2";

        // TODO: Check valid api key
        config = new Config();
        config.setApiKey(apiKey);
        config.setAccessToken(null);
        config.setTestMode(false);

        initUniRest(proxy);
    }


    /**
     * Set the access token, the requests will use the access token instead of the api key
     *
     * @param accessToken The access token (Generated or Organization token)
     */
    public void setAccessToken(String accessToken) {
        // TODO: Check valid access token
        config.setAccessToken(accessToken);
    }

    /**
     * Removes the access token, the requests will start using the api key again
     */
    public void revokeAccessToken() {
        config.setAccessToken(null);
    }

    /**
     * Enable test mode if you are using an access token
     */
    public void enableTestMode() {
        config.setTestMode(true);
    }

    /**
     * Disable test mode if you are using an access token
     */
    public void disableTestMode() {
        config.setTestMode(false);
    }

    /**
     * Set the user agent string
     */
    public void setUserAgentString(String userAgentString) {
        config.setUserAgentString(userAgentString);
    }

    /**
     * Handles connect actions
     *
     * @return ConnectHandler object
     */
    public ConnectHandler connect() {
        return new ConnectHandler(config);
    }

    /**
     * Handles payment actions
     *
     * @return PaymentHandler object
     */
    public PaymentHandler payments() {
        return new PaymentHandler(endpoint, config);
    }

    /**
     * Handles method actions
     *
     * @return MethodHandler object
     */
    public MethodHandler methods() {
        return new MethodHandler(endpoint, config);
    }

    /**
     * Handles refund actions
     *
     * @return RefundHandler object
     */
    public RefundHandler refunds() {
        return new RefundHandler(endpoint, config);
    }

    /**
     * Handles charge back actions
     *
     * @return ChargebackHandler object
     */
    public ChargebackHandler chargebacks() {
        return new ChargebackHandler(endpoint, config);
    }

    /**
     * Handles capture actions
     *
     * @return CaptureHandler object
     */
    public CaptureHandler captures() {
        return new CaptureHandler(endpoint, config);
    }

    /**
     * Handles order actions
     *
     * @return OrderHandler object
     */
    public OrderHandler orders() {
        return new OrderHandler(endpoint, config);
    }

    /**
     * Handles shipment actions
     *
     * @return ShipmentHandler object
     */
    public ShipmentHandler shipments() {
        return new ShipmentHandler(endpoint, config);
    }

    /**
     * Handles customer actions
     *
     * @return CustomerHandler object
     */
    public CustomerHandler customers() {
        return new CustomerHandler(endpoint, config);
    }

    /**
     * Handles mandate actions
     *
     * @return MandateHandler object
     */
    public MandateHandler mandates() {
        return new MandateHandler(endpoint, config);
    }

    /**
     * Handles subscription actions
     *
     * @return MethodHandler object
     */
    public SubscriptionHandler subscriptions() {
        return new SubscriptionHandler(endpoint, config);
    }

    /**
     * Handles permission actions
     *
     * @return PermissionHandler object
     */
    public PermissionHandler permissions() {
        return new PermissionHandler(endpoint, config);
    }

    /**
     * Handles organization actions
     *
     * @return OrganizationHandler object
     */
    public OrganizationHandler organizations() {
        return new OrganizationHandler(endpoint, config);
    }

    /**
     * Handles profile actions
     *
     * @return ProfileHandler object
     */
    public ProfileHandler profiles() {
        return new ProfileHandler(endpoint, config);
    }

    /**
     * Handles on boarding actions
     *
     * @return OnboardingHandler object
     */
    public OnboardingHandler onboarding() {
        return new OnboardingHandler(endpoint, config);
    }

    /**
     * Handles settlements actions
     *
     * @return SettlementHandler object
     */
    public SettlementHandler settlements() {
        return new SettlementHandler(endpoint, config);
    }

    /**
     * Handles invoices actions
     *
     * @return InvoiceHandler object
     */
    public InvoiceHandler invoices() {
        return new InvoiceHandler(endpoint, config);
    }

    /**
     * Handles miscellaneous actions
     *
     * @return MiscellaneousHandler object
     */
    public MiscellaneousHandler miscellaneous() {
        return new MiscellaneousHandler(endpoint, config);
    }

    private void initUniRest(ClientProxy proxy) {
        Unirest.config()
            .setObjectMapper(new OAuthAwareObjectMapper(config));

        if (proxy != null) {
            Unirest.config().proxy(proxy.getHost(), proxy.getPort(), proxy.getUsername(), proxy.getPassword());
        }
    }
}
