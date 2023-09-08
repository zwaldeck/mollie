package be.woutschoovaerts.mollie.handler.connect;

import be.woutschoovaerts.mollie.data.onboarding.OnboardingRequest;
import be.woutschoovaerts.mollie.data.onboarding.OnboardingResponse;
import be.woutschoovaerts.mollie.exception.MollieException;
import be.woutschoovaerts.mollie.util.QueryParams;
import be.woutschoovaerts.mollie.util.RestService;
import com.fasterxml.jackson.core.type.TypeReference;
import kong.unirest.UnirestException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Handles the onboarding API <a href="https://docs.mollie.com/reference/v2/onboarding-api/get-onboarding-status">Mollie docs</a>
 *
 * @author Wout Schoovaerts
 */
@RequiredArgsConstructor
public class OnboardingHandler {

    private static final Logger log = LoggerFactory.getLogger(OnboardingHandler.class);

    private static final TypeReference<OnboardingResponse> ONBOARDING_RESPONSE_TYPE = new TypeReference<>() {
    };

    private final RestService restService;

    /**
     * Get the status of onboarding of the authenticated organization.
     *
     * @return OnboardingResponse object
     * @throws MollieException when something went wrong
     */
    public OnboardingResponse getOnboardingStatus() throws MollieException {
        return getOnboardingStatus(new QueryParams());
    }

    /**
     * Get the status of onboarding of the authenticated organization.
     *
     * @param params A map of query params
     * @return OnboardingResponse object
     * @throws MollieException when something went wrong
     */
    public OnboardingResponse getOnboardingStatus(QueryParams params) throws MollieException {
        try {
            String uri = "/onboarding/me";

            return restService.get(uri, params, false, ONBOARDING_RESPONSE_TYPE);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Submit data that will be prefilled in the merchant’s onboarding. Please note that the data you submit will only be processed when the onboarding status is needs-data.
     *
     * @param body   OnboardingRequest object
     * @throws MollieException when something went wrong
     * @deprecated Use ClientLinkHandler.createClientLink()
     */
    @Deprecated
    public void submitOnboardingData(OnboardingRequest body) throws MollieException {
        submitOnboardingData(body, new QueryParams());
    }

    /**
     * Submit data that will be prefilled in the merchant’s onboarding. Please note that the data you submit will only be processed when the onboarding status is needs-data.
     *
     * @param body   OnboardingRequest object
     * @param params A map of query params
     * @throws MollieException when something went wrong
     * @deprecated Use ClientLinkHandler.createClientLink()
     */
    @Deprecated
    public void submitOnboardingData(OnboardingRequest body, QueryParams params) throws MollieException {
        try {
            String uri = "/onboarding/me";

            restService.post(uri, body, params, new TypeReference<Void>() {});
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

}
