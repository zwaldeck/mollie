package be.woutschoovaerts.mollie.handler;

import be.woutschoovaerts.mollie.data.miscellaneous.ApplePaySessionResponse;
import be.woutschoovaerts.mollie.exception.MollieException;
import be.woutschoovaerts.mollie.util.Config;
import be.woutschoovaerts.mollie.util.ObjectMapperService;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MiscellaneousHandler extends AbstractHandler {

    private static final Logger log = LoggerFactory.getLogger(MiscellaneousHandler.class);

    public MiscellaneousHandler(String baseApiUrl, Config config) {
        super(baseApiUrl, log, config);
    }

    /**
     * For integrating Apple Pay in your own checkout on the web, you need to provide merchant validation. This is normally done using Apple’s Requesting Apple Pay Session. The merchant validation proves (to Apple) that a validated merchant is calling the Apple Pay Javascript APIs.
     * <p>
     * When integrating Apple Pay via Mollie, you cannot call Apple’s API but you should call this API instead. The response of this API call should be passed as-is to the the completion method, completeMerchantValidation.
     * <p>
     * Before requesting an Apple Pay Payment Session, you must place the domain validation file on your server at: https://[domain]/.well-known/apple-developer-merchantid-domain-association. Without this file, it will not be possible to use Apple Pay on your domain.
     * <p>
     * The guidelines for working with a payment session are:
     *
     * <ul>
     * <li>Request a new payment session object for each transaction. You can only use a merchant session object a single time.</li>
     * <li>The payment session object expires five minutes after it is created.</li>
     * <li>Never request the payment session from the browser. The request must be sent from your server.</li>
     * <li>For the full documentation, see the official Apple Pay JS API documentation.</li>
     * </ul>
     *
     * @param profileId A profile ID
     * @return ApplePaySessionResponse object
     * @throws MollieException when something went wrong
     */
    public ApplePaySessionResponse requestApplePaySession(String profileId) throws MollieException {
        if (config.getAccessToken() == null) {
            throw new MollieException(
                    "To request an apple pay session with only a profile ID you must use an access token",
                    new HashMap<>()
            );
        }

        try {
            String uri = "/wallets/applepay/sessions";

            Map<String, Object> body = new HashMap<>();
            body.put("profileId", profileId);

            HttpResponse<String> response = post(uri, body);

            return ObjectMapperService.getInstance().getMapper()
                    .readValue(response.getBody(), ApplePaySessionResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * For integrating Apple Pay in your own checkout on the web, you need to provide merchant validation. This is normally done using Apple’s Requesting Apple Pay Session. The merchant validation proves (to Apple) that a validated merchant is calling the Apple Pay Javascript APIs.
     * <p>
     * When integrating Apple Pay via Mollie, you cannot call Apple’s API but you should call this API instead. The response of this API call should be passed as-is to the the completion method, completeMerchantValidation.
     * <p>
     * Before requesting an Apple Pay Payment Session, you must place the domain validation file on your server at: https://[domain]/.well-known/apple-developer-merchantid-domain-association. Without this file, it will not be possible to use Apple Pay on your domain.
     * <p>
     * The guidelines for working with a payment session are:
     *
     * <ul>
     * <li>Request a new payment session object for each transaction. You can only use a merchant session object a single time.</li>
     * <li>The payment session object expires five minutes after it is created.</li>
     * <li>Never request the payment session from the browser. The request must be sent from your server.</li>
     * <li>For the full documentation, see the official Apple Pay JS API documentation.</li>
     * </ul>
     *
     * @param validationUrl A validation url
     * @param domain        a domain
     * @return ApplePaySessionResponse object
     * @throws MollieException when something went wrong
     */
    public ApplePaySessionResponse requestApplePaySession(String validationUrl, String domain) throws MollieException {
        try {
            String uri = "/wallets/applepay/sessions";

            Map<String, Object> body = new HashMap<>();
            body.put("validationUrl", validationUrl);
            body.put("domain", domain);

            HttpResponse<String> response = post(uri, body);

            return ObjectMapperService.getInstance().getMapper()
                    .readValue(response.getBody(), ApplePaySessionResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }
}
