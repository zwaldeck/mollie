package be.feelio.mollie.handler;

import be.feelio.mollie.data.onboarding.OnboardingRequest;
import be.feelio.mollie.data.onboarding.OnboardingResponse;
import be.feelio.mollie.exception.MollieException;
import be.feelio.mollie.util.ObjectMapperService;
import be.feelio.mollie.util.QueryParams;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class OnboardingHandler extends AbstractHandler {

    private static final Logger log = LoggerFactory.getLogger(OnboardingHandler.class);

    public OnboardingHandler(String baseUrl) {
        super(baseUrl, log);
    }

    public OnboardingResponse getOnboardingStatus() throws MollieException {
        return getOnboardingStatus(QueryParams.EMPTY);
    }

    public OnboardingResponse getOnboardingStatus(QueryParams params) throws MollieException {
        try {
            String uri = "/onboarding/me";

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper()
                    .readValue(response.getBody(), OnboardingResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    public void submitOnboardingData(OnboardingRequest body) throws MollieException {
        submitOnboardingData(body, QueryParams.EMPTY);
    }

    public void submitOnboardingData(OnboardingRequest body, QueryParams params) throws MollieException {
        try {
            String uri = "/onboarding/me";

            post(uri, body, params);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

}
