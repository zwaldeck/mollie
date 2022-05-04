package be.woutschoovaerts.mollie.handler;

import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.data.method.MethodResponse;
import be.woutschoovaerts.mollie.data.payment.PaymentMethod;
import be.woutschoovaerts.mollie.data.profile.ProfileListResponse;
import be.woutschoovaerts.mollie.data.profile.ProfileRequest;
import be.woutschoovaerts.mollie.data.profile.ProfileResponse;
import be.woutschoovaerts.mollie.exception.MollieException;
import be.woutschoovaerts.mollie.util.Config;
import be.woutschoovaerts.mollie.util.ObjectMapperService;
import be.woutschoovaerts.mollie.util.QueryParams;
import com.fasterxml.jackson.core.type.TypeReference;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Handles the profiles API <a href="https://docs.mollie.com/reference/v2/profiles-api/create-profile">Mollie docs</a>
 *
 * @author Wout Schoovaerts
 */
public class ProfileHandler extends AbstractHandler {

    private static final Logger log = LoggerFactory.getLogger(ProfileHandler.class);

    public ProfileHandler(String baseUrl, Config config) {
        super(baseUrl, log, config);
    }

    /**
     * In order to process payments, you need to create a website profile. A website profile can easily be created via the Dashboard manually. However, the Mollie API also allows automatic profile creation via the Profiles API.
     *
     * @param body ProfileRequest object
     * @return ProfileResponse object
     * @throws MollieException when something went wrong
     */
    public ProfileResponse createProfile(ProfileRequest body) throws MollieException {
        return createProfile(body, new QueryParams());
    }

    /**
     * In order to process payments, you need to create a website profile. A website profile can easily be created via the Dashboard manually. However, the Mollie API also allows automatic profile creation via the Profiles API.
     *
     * @param body   ProfileRequest object
     * @param params A map of query params
     * @return ProfileResponse object
     * @throws MollieException when something went wrong
     */
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

    /**
     * Retrieve details of a profile, using the profile’s identifier.
     *
     * @param id A profile ID
     * @return ProfileResponse object
     * @throws MollieException when something went wrong
     */
    public ProfileResponse getProfile(String id) throws MollieException {
        return getProfile(id, new QueryParams());
    }

    /**
     * Retrieve details of a profile, using the profile’s identifier.
     *
     * @param id     A profile ID
     * @param params A map of query params
     * @return ProfileResponse object
     * @throws MollieException when something went wrong
     */
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

    /**
     * Use this API if you are creating a plugin or SaaS application that allows users to enter a Mollie API key, and you want to give a confirmation of the website profile that will be used in your plugin or application.
     * <p>
     * This is similar to the Get current organization endpoint for OAuth.
     *
     * @return ProfileResponse object
     * @throws MollieException when something went wrong
     */
    public ProfileResponse getMyProfile() throws MollieException {
        return getMyProfile(new QueryParams());
    }

    /**
     * Use this API if you are creating a plugin or SaaS application that allows users to enter a Mollie API key, and you want to give a confirmation of the website profile that will be used in your plugin or application.
     * <p>
     * This is similar to the Get current organization endpoint for OAuth.
     *
     * @param params A map of query params
     * @return ProfileResponse object
     * @throws MollieException when something went wrong
     */
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

    /**
     * A profile is required to process payments. A profile can easily be created and updated via the Dashboard manually. However, the Mollie API also allows automatic profile creation and updates via the Profiles API.
     *
     * @param id   A profile ID
     * @param body ProfileRequest Object
     * @return ProfileResponse object
     * @throws MollieException when something went wrong
     */
    public ProfileResponse updateProfile(String id, ProfileRequest body) throws MollieException {
        return updateProfile(id, body, new QueryParams());
    }

    /**
     * A profile is required to process payments. A profile can easily be created and updated via the Dashboard manually. However, the Mollie API also allows automatic profile creation and updates via the Profiles API.
     *
     * @param id     A profile ID
     * @param body   ProfileRequest Object
     * @param params A map of query params
     * @return ProfileResponse object
     * @throws MollieException when something went wrong
     */
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

    /**
     * This endpoint enables profile deletions, rendering the profile unavailable for further API calls and transactions.
     *
     * @param id A profile ID
     * @throws MollieException when something went wrong
     */
    public void deleteProfile(String id) throws MollieException {
        deleteProfile(id, new QueryParams());
    }

    /**
     * This endpoint enables profile deletions, rendering the profile unavailable for further API calls and transactions.
     *
     * @param id     A profile ID
     * @param params A map of query params
     * @throws MollieException when something went wrong
     */
    public void deleteProfile(String id, QueryParams params) throws MollieException {
        try {
            String uri = "/profiles/" + id;

            delete(uri, params);

        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Enable a payment method on an authenticated profile to use it with payments.
     *
     * @param paymentMethod A payment method
     * @return MethodResponse object
     * @throws MollieException when something went wrong
     */
    public MethodResponse enableMyPaymentMethod(PaymentMethod paymentMethod) throws MollieException {
        return enablePaymentMethod("me", paymentMethod, new QueryParams());
    }

    /**
     * Enable a payment method on an authenticated profile to use it with payments.
     *
     * @param paymentMethod A payment method
     * @param params        A map of query params
     * @return MethodResponse object
     * @throws MollieException when something went wrong
     */
    public MethodResponse enableMyPaymentMethod(PaymentMethod paymentMethod, QueryParams params)
            throws MollieException {
        return enablePaymentMethod("me", paymentMethod, params);
    }

    /**
     * Enable a payment method on a specific profile to use it with payments.
     *
     * @param profileId     A profile ID
     * @param paymentMethod A payment method
     * @return MethodResponse object
     * @throws MollieException when something went wrong
     */
    public MethodResponse enablePaymentMethod(String profileId, PaymentMethod paymentMethod) throws MollieException {
        return enablePaymentMethod(profileId, paymentMethod, new QueryParams());
    }

    /**
     * Enable a payment method on a specific  profile to use it with payments.
     *
     * @param profileId     A profile ID
     * @param paymentMethod A payment method
     * @param params        A map of query params
     * @return MethodResponse object
     * @throws MollieException when something went wrong
     */
    public MethodResponse enablePaymentMethod(String profileId, PaymentMethod paymentMethod, QueryParams params)
            throws MollieException {
        try {
            String uri = "/profiles/" + profileId + "/methods/" + paymentMethod.getJsonValue();

            HttpResponse<String> response = postWithoutBody(uri, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(), MethodResponse.class);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Disable a payment method on an authenticated profile.
     *
     * @param paymentMethod A payment method
     * @throws MollieException when something went wrong
     */
    public void disableMyPaymentMethod(PaymentMethod paymentMethod) throws MollieException {
        enablePaymentMethod("me", paymentMethod, new QueryParams());
    }

    /**
     * Disable a payment method on an authenticated profile.
     *
     * @param paymentMethod A payment method
     * @param params        A map of query params
     * @throws MollieException when something went wrong
     */
    public void disableMyPaymentMethod(PaymentMethod paymentMethod, QueryParams params)
            throws MollieException {
        disablePaymentMethod("me", paymentMethod, params);
    }

    /**
     * Disable a payment method on a specific profile.
     *
     * @param profileId     A profile ID
     * @param paymentMethod A payment method
     * @throws MollieException when something went wrong
     */
    public void disablePaymentMethod(String profileId, PaymentMethod paymentMethod) throws MollieException {
        disablePaymentMethod(profileId, paymentMethod, new QueryParams());
    }

    /**
     * Disable a payment method on a specific profile.
     *
     * @param profileId     A profile ID
     * @param paymentMethod A payment method
     * @param params        A map of query params
     * @throws MollieException when something went wrong
     */
    public void disablePaymentMethod(String profileId, PaymentMethod paymentMethod, QueryParams params)
            throws MollieException {
        try {
            String uri = "/profiles/" + profileId + "/methods/" + paymentMethod.getJsonValue();

            delete(uri, params);
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

    /**
     * Retrieve all profiles available on the account.
     *
     * @return Pagination<ProfileListResponse> object
     * @throws MollieException when something went wrong
     */
    public Pagination<ProfileListResponse> getProfiles() throws MollieException {
        return getProfiles(new QueryParams());
    }

    /**
     * Retrieve all profiles available on the account.
     *
     * @param params A map of query params
     * @return Pagination<ProfileListResponse> object
     * @throws MollieException when something went wrong
     */
    public Pagination<ProfileListResponse> getProfiles(QueryParams params) throws MollieException {
        try {
            String uri = "/profiles";

            HttpResponse<String> response = get(uri, params);

            return ObjectMapperService.getInstance().getMapper().readValue(response.getBody(),
                    new TypeReference<Pagination<ProfileListResponse>>() {
                    });
        } catch (UnirestException | IOException ex) {
            log.error("An unexpected exception occurred", ex);
            throw new MollieException(ex);
        }
    }

}
