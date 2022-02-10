package be.woutschoovaerts.mollie.handler;

import be.woutschoovaerts.mollie.data.onboarding.OnboardingRequest;
import be.woutschoovaerts.mollie.data.onboarding.OnboardingResponse;
import be.woutschoovaerts.mollie.exception.MollieException;
import be.woutschoovaerts.mollie.util.Config;
import be.woutschoovaerts.mollie.util.QueryParams;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles the onboarding API <a href="https://docs.mollie.com/reference/v2/onboarding-api/get-onboarding-status">Mollie docs</a>
 *
 * @author Wout Schoovaerts
 */
public class OnboardingHandler extends AbstractHandler {

    private static final Logger log = LoggerFactory.getLogger(OnboardingHandler.class);

    public OnboardingHandler(String baseUrl, Config config) {
        super(baseUrl, log, config);
    }

    /**
     * Get the status of onboarding of the authenticated organization.
     *
     * @return OnboardingResponse object
     * @throws MollieException when something went wrong
     */
    public OnboardingResponse getOnboardingStatus() throws MollieException {
        return getOnboardingStatus(QueryParams.EMPTY);
    }

    /**
     * Get the status of onboarding of the authenticated organization.
     *
     * @param params A map of query params
     * @return OnboardingResponse object
     * @throws MollieException when something went wrong
     */
    public OnboardingResponse getOnboardingStatus(QueryParams params) throws MollieException {
        String uri = "/onboarding/me";

        return get(uri, params, new TypeReference<>() {
        });
    }

    /**
     * Submit data that will be prefilled in the merchant’s onboarding. Please note that the data you submit will only be processed when the onboarding status is needs-data.
     *
     * @param body   OnboardingRequest object
     * @throws MollieException when something went wrong
     */
    public void submitOnboardingData(OnboardingRequest body) throws MollieException {
        submitOnboardingData(body, QueryParams.EMPTY);
    }

    /**
     * Submit data that will be prefilled in the merchant’s onboarding. Please note that the data you submit will only be processed when the onboarding status is needs-data.
     *
     * @param body   OnboardingRequest object
     * @param params A map of query params
     * @throws MollieException when something went wrong
     */
    public void submitOnboardingData(OnboardingRequest body, QueryParams params) throws MollieException {
        String uri = "/onboarding/me";

        post(uri, body, params, new TypeReference<>() {
        });
    }
}
