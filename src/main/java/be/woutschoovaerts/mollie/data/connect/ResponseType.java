package be.woutschoovaerts.mollie.data.connect;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseType {

    CODE("code");

    private final String value;
}
