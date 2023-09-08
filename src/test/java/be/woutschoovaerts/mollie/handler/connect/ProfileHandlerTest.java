package be.woutschoovaerts.mollie.handler.connect;

import be.woutschoovaerts.mollie.data.payment.PaymentMethod;
import be.woutschoovaerts.mollie.data.profile.EnableVoucherIssuerRequest;
import be.woutschoovaerts.mollie.data.profile.ProfileRequest;
import be.woutschoovaerts.mollie.data.profile.UpdateProfileRequest;
import be.woutschoovaerts.mollie.util.QueryParams;
import be.woutschoovaerts.mollie.util.RestService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProfileHandlerTest {

    @Mock
    private RestService restService;

    @InjectMocks
    private ProfileHandler handler;

    @Test
    void createProfile() throws Exception {
        String uri = "/profiles";

        ProfileRequest request = ProfileRequest.builder()
                .name("wout")
                .email("fake@email.com")
                .website("https://z-soft.be")
                .phone("0032487767055")
                .build();

        handler.createProfile(request);

        verify(restService).post(eq(uri), eq(request), any(QueryParams.class), any(TypeReference.class));
    }

    @Test
    void getProfile() throws Exception {
        String uri = "/profiles/profile_id";

        handler.getProfile("profile_id");

        verify(restService).get(eq(uri), any(QueryParams.class), eq(false), any(TypeReference.class));
    }

    @Test
    void getMyProfile() throws Exception {
        String uri = "/profiles/me";

        handler.getMyProfile();

        verify(restService).get(eq(uri), any(QueryParams.class), eq(false), any(TypeReference.class));
    }

    @Test
    void updateProfile() throws Exception {
        String uri = "/profiles/profile_id";

        UpdateProfileRequest request = UpdateProfileRequest.builder()
                .name(Optional.of("wout"))
                .build();

        handler.updateProfile("profile_id", request);

        verify(restService).patch(eq(uri), eq(request), any(QueryParams.class), any(TypeReference.class));
    }

    @Test
    void deleteProfile() throws Exception {
        String uri = "/profiles/profile_id";

        handler.deleteProfile("profile_id");

        verify(restService).delete(eq(uri), any(QueryParams.class), eq(false), any(TypeReference.class));
    }

    @Test
    void enableMyPaymentMethod() throws Exception {
        String uri = "/profiles/me/methods/bancontact";

        handler.enableMyPaymentMethod(PaymentMethod.BANCONTACT);

        verify(restService).postWithoutBody(eq(uri), any(QueryParams.class), any(TypeReference.class));
    }

    @Test
    void disablePaymentMethod() throws Exception {
        String uri = "/profiles/me/methods/bancontact";

        handler.disableMyPaymentMethod(PaymentMethod.BANCONTACT);

        verify(restService).delete(eq(uri), any(QueryParams.class), eq(false), any(TypeReference.class));
    }

    @Test
    void getProfiles() throws Exception {
        String uri = "/profiles";

        handler.getProfiles();

        verify(restService).get(eq(uri), any(QueryParams.class), eq(false), any(TypeReference.class));
    }

    @Test
    void enableGiftCardIssuer() throws Exception {
        String uri = "/profiles/profile_id/methods/giftcard/issuers/wout";

        handler.enableGiftCardIssuer("profile_id", "wout");

        verify(restService).postWithoutBody(eq(uri), any(QueryParams.class), any(TypeReference.class));
    }

    @Test
    void disableGiftCardIssuer() throws Exception {
        String uri = "/profiles/profile_id/methods/giftcard/issuers/wout";

        handler.disableGiftCardIssuer("profile_id", "wout");

        verify(restService).delete(eq(uri), any(QueryParams.class), eq(false), any(TypeReference.class));
    }

    @Test
    void enableVoucherIssuer() throws Exception {
        String uri = "/profiles/profile_id/methods/voucher/issuers/wout";

        EnableVoucherIssuerRequest request = EnableVoucherIssuerRequest.builder()
                .build();

        handler.enableVoucherIssuer("profile_id", "wout", request);

        verify(restService).post(eq(uri), eq(request), any(QueryParams.class), any(TypeReference.class));
    }

}