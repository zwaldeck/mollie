package be.woutschoovaerts.mollie.handler;

import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.data.method.MethodResponse;
import be.woutschoovaerts.mollie.data.payment.PaymentMethod;
import be.woutschoovaerts.mollie.data.profile.ProfileListResponse;
import be.woutschoovaerts.mollie.data.profile.ProfileRequest;
import be.woutschoovaerts.mollie.data.profile.ProfileResponse;
import be.woutschoovaerts.mollie.exception.MollieException;
import be.woutschoovaerts.mollie.util.Config;
import be.woutschoovaerts.mollie.util.QueryParams;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        return createProfile(body, QueryParams.EMPTY);
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
        String uri = "/profiles";

        return post(uri, body, params, new TypeReference<>() {
        });
    }

    /**
     * Retrieve details of a profile, using the profile’s identifier.
     *
     * @param id A profile ID
     * @return ProfileResponse object
     * @throws MollieException when something went wrong
     */
    public ProfileResponse getProfile(String id) throws MollieException {
        return getProfile(id, QueryParams.EMPTY);
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
        String uri = "/profiles/" + id;

        return get(uri, params, new TypeReference<>() {
        });
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
        return getMyProfile(QueryParams.EMPTY);
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
        String uri = "/profiles/me";

        return get(uri, params, new TypeReference<>() {
        });
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
        return updateProfile(id, body, QueryParams.EMPTY);
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
        String uri = "/profiles/" + id;

        return patch(uri, body, params, new TypeReference<>() {
        });
    }

    /**
     * This endpoint enables profile deletions, rendering the profile unavailable for further API calls and transactions.
     *
     * @param id A profile ID
     * @throws MollieException when something went wrong
     */
    public void deleteProfile(String id) throws MollieException {
        deleteProfile(id, QueryParams.EMPTY);
    }

    /**
     * This endpoint enables profile deletions, rendering the profile unavailable for further API calls and transactions.
     *
     * @param id     A profile ID
     * @param params A map of query params
     * @throws MollieException when something went wrong
     */
    public void deleteProfile(String id, QueryParams params) throws MollieException {
        String uri = "/profiles/" + id;

        delete(uri, params, new TypeReference<>() {
        });
    }

    /**
     * Enable a payment method on an authenticated profile to use it with payments.
     *
     * @param paymentMethod A payment method
     * @return MethodResponse object
     * @throws MollieException when something went wrong
     */
    public MethodResponse enableMyPaymentMethod(PaymentMethod paymentMethod) throws MollieException {
        return enablePaymentMethod("me", paymentMethod, QueryParams.EMPTY);
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
        return enablePaymentMethod(profileId, paymentMethod, QueryParams.EMPTY);
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
        String uri = "/profiles/" + profileId + "/methods/" + paymentMethod.getJsonValue();

        return postWithoutBody(uri, params, new TypeReference<>() {
        });
    }

    /**
     * Disable a payment method on an authenticated profile.
     *
     * @param paymentMethod A payment method
     * @throws MollieException when something went wrong
     */
    public void disableMyPaymentMethod(PaymentMethod paymentMethod) throws MollieException {
        enablePaymentMethod("me", paymentMethod, QueryParams.EMPTY);
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
        disablePaymentMethod(profileId, paymentMethod, QueryParams.EMPTY);
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
        String uri = "/profiles/" + profileId + "/methods/" + paymentMethod.getJsonValue();

        delete(uri, params, new TypeReference<>() {
        });
    }

    /**
     * Retrieve all profiles available on the account.
     *
     * @return Pagination<ProfileListResponse> object
     * @throws MollieException when something went wrong
     */
    public Pagination<ProfileListResponse> getProfiles() throws MollieException {
        return getProfiles(QueryParams.EMPTY);
    }

    /**
     * Retrieve all profiles available on the account.
     *
     * @param params A map of query params
     * @return Pagination<ProfileListResponse> object
     * @throws MollieException when something went wrong
     */
    public Pagination<ProfileListResponse> getProfiles(QueryParams params) throws MollieException {
        String uri = "/profiles";

        return get(uri, params, new TypeReference<>() {
        });
    }
}
