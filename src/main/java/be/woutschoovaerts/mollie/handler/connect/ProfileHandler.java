package be.woutschoovaerts.mollie.handler.connect;

import be.woutschoovaerts.mollie.data.common.Pagination;
import be.woutschoovaerts.mollie.data.method.MethodResponse;
import be.woutschoovaerts.mollie.data.payment.PaymentMethod;
import be.woutschoovaerts.mollie.data.profile.*;
import be.woutschoovaerts.mollie.exception.MollieException;
import be.woutschoovaerts.mollie.util.QueryParams;
import be.woutschoovaerts.mollie.util.RestService;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;

/**
 * Handles the profiles API <a href="https://docs.mollie.com/reference/v2/profiles-api/create-profile">Mollie docs</a>
 *
 * @author Wout Schoovaerts
 */
@RequiredArgsConstructor
public class ProfileHandler {

    private static final TypeReference<ProfileResponse> PROFILE_RESPONSE_TYPE = new TypeReference<>() {
    };
    private static final TypeReference<MethodResponse> METHOD_RESPONSE_TYPE = new TypeReference<>() {
    };
    private static final TypeReference<Pagination<ProfileListResponse>> PROFILE_LIST_RESPONSE_TYPE = new TypeReference<>() {
    };
    private static final TypeReference<GiftCardIssuerResponse> GIFT_CARD_ISSUER_RESPONSE_TYPE = new TypeReference<>() {
    };
    private static final TypeReference<VoucherIssuerResponse> VOUCHER_ISSUER_RESPONSE_TYPE = new TypeReference<>() {
    };

    private final RestService restService;

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
            String uri = "/profiles";

            return restService.post(uri, body, params, PROFILE_RESPONSE_TYPE);
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
            String uri = "/profiles/" + id;

            return  restService.get(uri, params, false, PROFILE_RESPONSE_TYPE);
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
            String uri = "/profiles/me";

            return restService.get(uri, params, false, PROFILE_RESPONSE_TYPE);
    }

    /**
     * A profile is required to process payments. A profile can easily be created and updated via the Dashboard manually. However, the Mollie API also allows automatic profile creation and updates via the Profiles API.
     *
     * @param id   A profile ID
     * @param body ProfileRequest Object
     * @return ProfileResponse object
     * @throws MollieException when something went wrong
     */
    public ProfileResponse updateProfile(String id, UpdateProfileRequest body) throws MollieException {
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
    public ProfileResponse updateProfile(String id, UpdateProfileRequest body, QueryParams params) throws MollieException {
            String uri = "/profiles/" + id;

            return restService.patch(uri, body, params, PROFILE_RESPONSE_TYPE);
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
            String uri = "/profiles/" + id;

            restService.delete(uri, params, false, new TypeReference<Void>(){});
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
            String uri = "/profiles/" + profileId + "/methods/" + paymentMethod.getJsonValue();

            return restService.postWithoutBody(uri, params, METHOD_RESPONSE_TYPE);
    }

    /**
     * Disable a payment method on an authenticated profile.
     *
     * @param paymentMethod A payment method
     * @throws MollieException when something went wrong
     */
    public void disableMyPaymentMethod(PaymentMethod paymentMethod) throws MollieException {
        disablePaymentMethod("me", paymentMethod, new QueryParams());
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
            String uri = "/profiles/" + profileId + "/methods/" + paymentMethod.getJsonValue();

            restService.delete(uri, params, false, new TypeReference<Void>(){});
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
            String uri = "/profiles";

            return restService.get(uri, params, false, PROFILE_LIST_RESPONSE_TYPE);
    }

    public GiftCardIssuerResponse enableMyGiftCardIssuer(String issuer) throws MollieException {
        return enableMyGiftCardIssuer(issuer, new QueryParams());
    }

    public GiftCardIssuerResponse enableMyGiftCardIssuer(String issuer, QueryParams queryParams) throws MollieException {
        return enableGiftCardIssuer("me", issuer, queryParams);
    }

    public GiftCardIssuerResponse enableGiftCardIssuer(String profileId, String issuer) throws MollieException {
        return enableGiftCardIssuer(profileId, issuer, new QueryParams());
    }

    public GiftCardIssuerResponse enableGiftCardIssuer(String profileId, String issuer, QueryParams queryParams) throws MollieException {
            String uri = "/profiles/" + profileId + "/methods/giftcard/issuers/" + issuer;

            return restService.postWithoutBody(uri, queryParams, GIFT_CARD_ISSUER_RESPONSE_TYPE);
    }

    public void disableMyGiftCardIssuer(String issuer) throws MollieException {
        disableMyGiftCardIssuer(issuer, new QueryParams());
    }

    public void disableMyGiftCardIssuer(String issuer, QueryParams queryParams) throws MollieException {
        disableGiftCardIssuer("me", issuer, queryParams);
    }

    public void disableGiftCardIssuer(String profileId, String issuer) throws MollieException {
        disableGiftCardIssuer(profileId, issuer, new QueryParams());
    }

    public void disableGiftCardIssuer(String profileId, String issuer, QueryParams params) throws MollieException {
            String uri = "/profiles/" + profileId + "/methods/giftcard/issuers/" + issuer;

            restService.delete(uri, params, false, new TypeReference<Void>(){});
    }

    public VoucherIssuerResponse enableMyVoucherIssuer(String issuer, EnableVoucherIssuerRequest request)
            throws MollieException {
        return enableVoucherIssuer("me", issuer, request, new QueryParams());
    }

    public VoucherIssuerResponse enableMyVoucherIssuer(String issuer, EnableVoucherIssuerRequest request, QueryParams queryParams)
            throws MollieException {
        return enableVoucherIssuer("me", issuer, request, queryParams);
    }

    public VoucherIssuerResponse enableVoucherIssuer(String profileId, String issuer, EnableVoucherIssuerRequest request)
            throws MollieException {
        return enableVoucherIssuer(profileId, issuer, request, new QueryParams());
    }

    public VoucherIssuerResponse enableVoucherIssuer(String profileId, String issuer,
                                                     EnableVoucherIssuerRequest request, QueryParams queryParams)
            throws MollieException {
            String uri = "/profiles/" + profileId + "/methods/voucher/issuers/" + issuer;

            return restService.post(uri, request, queryParams, VOUCHER_ISSUER_RESPONSE_TYPE);
    }
}
