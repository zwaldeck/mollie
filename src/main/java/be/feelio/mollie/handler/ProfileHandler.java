package be.feelio.mollie.handler;

import be.feelio.mollie.data.enums.PaymentMethod;
import be.feelio.mollie.data.request.ProfileRequest;
import be.feelio.mollie.data.response.MethodResponse;
import be.feelio.mollie.data.response.ProfileListResponse;
import be.feelio.mollie.data.response.ProfileResponse;
import be.feelio.mollie.exception.MollieException;
import be.feelio.mollie.util.ObjectMapperService;
import be.feelio.mollie.util.QueryParams;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ProfileHandler extends AbstractHandler {

    private static final Logger log = LoggerFactory.getLogger(ProfileHandler.class);

    public ProfileHandler(String baseUrl) {
        super(baseUrl, log);
    }

    public ProfileResponse createProfile(ProfileRequest body) throws MollieException {
        return createProfile(body, QueryParams.EMPTY);
    }

    public ProfileResponse createProfile(ProfileRequest body, QueryParams params) throws MollieException {
        try {
            String uri = "/profiles";

            HttpResponse<String> response = post(uri, body, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), ProfileResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    public ProfileResponse getProfile(String id) throws MollieException {
        return getProfile(id, QueryParams.EMPTY);
    }

    public ProfileResponse getProfile(String id, QueryParams params) throws MollieException {
        try {
            String uri = "/profiles/" + id;

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), ProfileResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    public ProfileResponse getMyProfile() throws MollieException {
        return getMyProfile(QueryParams.EMPTY);
    }

    public ProfileResponse getMyProfile(QueryParams params) throws MollieException {
        try {
            String uri = "/profiles/me";

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), ProfileResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    public ProfileResponse updateProfile(String id, ProfileRequest body) throws MollieException {
        return updateProfile(id, body, QueryParams.EMPTY);
    }

    public ProfileResponse updateProfile(String id, ProfileRequest body, QueryParams params) throws MollieException {
        try {
            String uri = "/profiles/" + id;

            HttpResponse<String> response = patch(uri, body, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), ProfileResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    public void deleteProfile(String id) throws MollieException {
        deleteProfile(id, QueryParams.EMPTY);
    }

    public void deleteProfile(String id, QueryParams params) throws MollieException {
        try {
            String uri = "/profiles/" + id;

            delete(uri, params);

        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    public MethodResponse enableMyPaymentMethod(PaymentMethod paymentMethod) throws MollieException {
        return enablePaymentMethod("me", paymentMethod, QueryParams.EMPTY);
    }

    public MethodResponse enableMyPaymentMethod(PaymentMethod paymentMethod, QueryParams params)
            throws MollieException {
        return enablePaymentMethod("me", paymentMethod, params);
    }

    public MethodResponse enablePaymentMethod(String profileId, PaymentMethod paymentMethod) throws MollieException {
        return enablePaymentMethod(profileId, paymentMethod, QueryParams.EMPTY);
    }

    public MethodResponse enablePaymentMethod(String id, PaymentMethod paymentMethod, QueryParams params)
            throws MollieException {
        try {
            String uri = "/profiles/" + id + "/methods/" + paymentMethod.getJsonValue();

            HttpResponse<String> response = postWithoutBody(uri, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), MethodResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    public void disableMyPaymentMethod(PaymentMethod paymentMethod) throws MollieException {
        enablePaymentMethod("me", paymentMethod, QueryParams.EMPTY);
    }

    public void disableMyPaymentMethod(PaymentMethod paymentMethod, QueryParams params)
            throws MollieException {
        disablePaymentMethod("me", paymentMethod, params);
    }

    public void disablePaymentMethod(String profileId, PaymentMethod paymentMethod) throws MollieException {
        disablePaymentMethod(profileId, paymentMethod, QueryParams.EMPTY);
    }

    public void disablePaymentMethod(String id, PaymentMethod paymentMethod, QueryParams params)
            throws MollieException {
        try {
            String uri = "/profiles/" + id + "/methods/" + paymentMethod.getJsonValue();

            delete(uri, params);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    public ProfileListResponse getProfiles() throws MollieException {
        return getProfiles(QueryParams.EMPTY);
    }

    public ProfileListResponse getProfiles(QueryParams params) throws MollieException {
        try {
            String uri = "/profiles";

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper()
                    .readValue(response.getBody(), ProfileListResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

}
