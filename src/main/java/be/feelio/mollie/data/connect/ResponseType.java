package be.feelio.mollie.data.connect;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseType {

    CODE("name");

    private final String value;
}
