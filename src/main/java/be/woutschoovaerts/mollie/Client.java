package be.woutschoovaerts.mollie;

import be.woutschoovaerts.mollie.handler.business.BalanceHandler;
import be.woutschoovaerts.mollie.handler.business.InvoiceHandler;
import be.woutschoovaerts.mollie.handler.business.SettlementHandler;
import be.woutschoovaerts.mollie.handler.client.ClientHandler;
import be.woutschoovaerts.mollie.handler.client.ClientLinkHandler;
import be.woutschoovaerts.mollie.handler.connect.*;
import be.woutschoovaerts.mollie.handler.orders.OrderHandler;
import be.woutschoovaerts.mollie.handler.orders.ShipmentHandler;
import be.woutschoovaerts.mollie.handler.payments.*;
import be.woutschoovaerts.mollie.handler.recurring.CustomerHandler;
import be.woutschoovaerts.mollie.handler.recurring.MandateHandler;
import be.woutschoovaerts.mollie.handler.recurring.SubscriptionHandler;
import be.woutschoovaerts.mollie.util.Config;
import be.woutschoovaerts.mollie.util.RestService;
import kong.unirest.Unirest;
import lombok.Getter;

public class Client {

    @Getter
    private final Config config;

    private final RestService restService;

    public Client(String apiKey) {
        this(apiKey, null);
    }

    public Client(String apiKey, ClientProxy proxy) {
        // TODO: Check valid api key
        config = new Config();
        config.setApiKey(apiKey);
        config.setAccessToken(null);
        config.setTestMode(false);
        config.setIdempotencyKey(null);

        restService = new RestService(config);

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

    public void setIdempotencyKey(String key) {
        config.setIdempotencyKey(key);
    }

    public void removeIdempotencyKey() {
        config.setIdempotencyKey(null);
    }

    /**
     * Set the user agent string
     */
    public void setUserAgentString(String userAgentString) {
        config.setUserAgentString(userAgentString);
    }

    /**
     * Handles OAuth actions
     *
     * @return OAuthHandler object
     */
    public OAuthHandler oAuth() {
        return new OAuthHandler(restService);
    }

    /**
     * Handles payment actions
     *
     * @return PaymentHandler object
     */
    public PaymentHandler payments() {
        return new PaymentHandler(restService);
    }

    /**
     * Handles payment link actions
     *
     * @return PaymentLinkHandler object
     */
    public PaymentLinkHandler paymentLinks() {
        return new PaymentLinkHandler(restService);
    }

    /**
     * Handles method actions
     *
     * @return MethodHandler object
     */
    public MethodHandler methods() {
        return new MethodHandler(restService);
    }

    /**
     * Handles refund actions
     *
     * @return RefundHandler object
     */
    public RefundHandler refunds() {
        return new RefundHandler(restService);
    }

    /**
     * Handles charge back actions
     *
     * @return ChargebackHandler object
     */
    public ChargebackHandler chargebacks() {
        return new ChargebackHandler(restService);
    }

    /**
     * Handles capture actions
     *
     * @return CaptureHandler object
     */
    public CaptureHandler captures() {
        return new CaptureHandler(restService);
    }

    /**
     * Handles order actions
     *
     * @return OrderHandler object
     */
    @Deprecated
    public OrderHandler orders() {
        return new OrderHandler(restService);
    }

    /**
     * Handles shipment actions
     *
     * @return ShipmentHandler object
     */
    @Deprecated
    public ShipmentHandler shipments() {
        return new ShipmentHandler(restService);
    }

    /**
     * Handles customer actions
     *
     * @return CustomerHandler object
     */
    public CustomerHandler customers() {
        return new CustomerHandler(restService);
    }

    /**
     * Handles mandate actions
     *
     * @return MandateHandler object
     */
    public MandateHandler mandates() {
        return new MandateHandler(restService);
    }

    /**
     * Handles subscription actions
     *
     * @return MethodHandler object
     */
    public SubscriptionHandler subscriptions() {
        return new SubscriptionHandler(restService);
    }

    /**
     * Handles permission actions
     *
     * @return PermissionHandler object
     */
    public PermissionHandler permissions() {
        return new PermissionHandler(restService);
    }

    /**
     * Handles organization actions
     *
     * @return OrganizationHandler object
     */
    public OrganizationHandler organizations() {
        return new OrganizationHandler(restService);
    }

    /**
     * Handles profile actions
     *
     * @return ProfileHandler object
     */
    public ProfileHandler profiles() {
        return new ProfileHandler(restService);
    }

    /**
     * Handles on boarding actions
     *
     * @return OnboardingHandler object
     */
    public OnboardingHandler onboarding() {
        return new OnboardingHandler(restService);
    }

    /**
     * Handles settlements actions
     *
     * @return SettlementHandler object
     */
    public SettlementHandler settlements() {
        return new SettlementHandler(restService);
    }

    /**
     * Handles invoices actions
     *
     * @return InvoiceHandler object
     */
    public InvoiceHandler invoices() {
        return new InvoiceHandler(restService);
    }

    /**
     * Handles wallet actions
     *
     * @return WalletHandler object
     */
    public WalletHandler wallet() {
        return new WalletHandler(restService);
    }

    /**
     * Handles balances actions
     *
     * @return BalanceHandler object
     */
    public BalanceHandler balances() {
        return new BalanceHandler(restService);
    }

    /**
     * Handles clients actions
     *
     * @return ClientHandler object
     */
    public ClientHandler clients() {
        return new ClientHandler(restService);
    }

    /**
     * Handles terminal actions
     *
     * @return TerminalHandler object
     */
    public TerminalHandler terminals() {
        return new TerminalHandler(restService);
    }

    /**
     * Handles client link actions
     *
     * @return ClientLinkHandler object
     */
    public ClientLinkHandler clientLinks() {
        return new ClientLinkHandler(restService);
    }

    private void initUniRest(ClientProxy proxy) {
        Unirest.config()
            .setObjectMapper(new JacksonObjectMapper());

        if (proxy != null) {
            Unirest.config().proxy(proxy.getHost(), proxy.getPort(), proxy.getUsername(), proxy.getPassword());
        }
    }
}
