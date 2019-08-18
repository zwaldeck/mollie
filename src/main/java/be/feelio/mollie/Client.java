package be.feelio.mollie;

import be.feelio.mollie.handler.*;
import be.feelio.mollie.util.Config;
import kong.unirest.Unirest;
import lombok.Getter;

public class Client {

    @Getter
    private final String endpoint;


    public Client(String apiKey) {
        this.endpoint = "https://api.mollie.com/v2";

        // TODO: Check valid api key
        Config.getInstance().setApiKey(apiKey);
        Config.getInstance().setAccessToken(null);
        Config.getInstance().setTestMode(false);

        initUniRest();
    }

    /**
     * Set the access token, the requests will use the access token instead of the api key
     *
     * @param accessToken The access token (Generated or Organization token)
     */
    public void setAccessToken(String accessToken) {
        // TODO: Check valid access token
        Config.getInstance().setAccessToken(accessToken);
    }

    /**
     * Removes the access token, the requests will start using the api key again
     */
    public void revokeAccessToken() {
        Config.getInstance().setAccessToken(null);
    }

    /**
     * Enable test mode if you are using an access token
     */
    public void enableTestMode() {
        Config.getInstance().setTestMode(true);
    }

    /**
     * Disable test mode if you are using an access token
     */
    public void disableTestMode() {
        Config.getInstance().setTestMode(false);
    }

    /**
     * Handles connect actions
     *
     * @return ConnectHandler object
     */
    public ConnectHandler connect() {
        return new ConnectHandler();
    }

    /**
     * Handles payment actions
     *
     * @return PaymentHandler object
     */
    public PaymentHandler payments() {
        return new PaymentHandler(endpoint);
    }

    /**
     * Handles method actions
     *
     * @return MethodHandler object
     */
    public MethodHandler methods() {
        return new MethodHandler(endpoint);
    }

    /**
     * Handles refund actions
     *
     * @return RefundHandler object
     */
    public RefundHandler refunds() {
        return new RefundHandler(endpoint);
    }

    /**
     * Handles charge back actions
     *
     * @return ChargebackHandler object
     */
    public ChargebackHandler chargebacks() {
        return new ChargebackHandler(endpoint);
    }

    /**
     * Handles capture actions
     *
     * @return CaptureHandler object
     */
    public CaptureHandler captures() {
        return new CaptureHandler(endpoint);
    }

    /**
     * Handles order actions
     *
     * @return OrderHandler object
     */
    public OrderHandler orders() {
        return new OrderHandler(endpoint);
    }

    /**
     * Handles shipment actions
     *
     * @return ShipmentHandler object
     */
    public ShipmentHandler shipments() {
        return new ShipmentHandler(endpoint);
    }

    /**
     * Handles customer actions
     *
     * @return CustomerHandler object
     */
    public CustomerHandler customers() {
        return new CustomerHandler(endpoint);
    }

    /**
     * Handles mandate actions
     *
     * @return MandateHandler object
     */
    public MandateHandler mandates() {
        return new MandateHandler(endpoint);
    }

    /**
     * Handles subscription actions
     *
     * @return MethodHandler object
     */
    public SubscriptionHandler subscriptions() {
        return new SubscriptionHandler(endpoint);
    }

    /**
     * Handles permission actions
     *
     * @return PermissionHandler object
     */
    public PermissionHandler permissions() {
        return new PermissionHandler(endpoint);
    }

    /**
     * Handles organization actions
     *
     * @return OrganizationHandler object
     */
    public OrganizationHandler organizations() {
        return new OrganizationHandler(endpoint);
    }

    /**
     * Handles profile actions
     *
     * @return ProfileHandler object
     */
    public ProfileHandler profiles() {
        return new ProfileHandler(endpoint);
    }

    /**
     * Handles on boarding actions
     *
     * @return OnboardingHandler object
     */
    public OnboardingHandler onboarding() {
        return new OnboardingHandler(endpoint);
    }

    /**
     * Handles settlements actions
     *
     * @return SettlementHandler object
     */
    public SettlementHandler settlements() {
        return new SettlementHandler(endpoint);
    }

    /**
     * Handles invoices actions
     *
     * @return InvoiceHandler object
     */
    public InvoiceHandler invoices() {
        return new InvoiceHandler(endpoint);
    }

    /**
     * Handles miscellaneous actions
     *
     * @return MiscellaneousHandler object
     */
    public MiscellaneousHandler miscellaneous() {
        return new MiscellaneousHandler(endpoint);
    }

    private void initUniRest() {
        Unirest.config()
            .setObjectMapper(new OAuthAwareObjectMapper());
    }
}
