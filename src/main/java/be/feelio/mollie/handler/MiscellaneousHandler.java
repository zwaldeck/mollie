package be.feelio.mollie.handler;

import be.feelio.mollie.data.response.ApplePaySessionResponse;
import be.feelio.mollie.exception.MollieException;
import be.feelio.mollie.util.Config;
import be.feelio.mollie.util.ObjectMapperService;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MiscellaneousHandler extends AbstractHandler {

    private static final Logger log = LoggerFactory.getLogger(MiscellaneousHandler.class);

    public MiscellaneousHandler(String baseApiUrl) {
        super(baseApiUrl, log);
    }

    public ApplePaySessionResponse requestApplePaySession(String profileId) throws MollieException {
        if (Config.getInstance().getAccessToken() == null) {
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
