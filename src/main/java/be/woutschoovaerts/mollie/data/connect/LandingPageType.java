package be.woutschoovaerts.mollie.data.connect;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LandingPageType {

    LOGIN("login"),
    SIGN_UP("signup");

    private final String value;
}
