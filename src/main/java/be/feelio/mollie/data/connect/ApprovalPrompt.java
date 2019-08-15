package be.feelio.mollie.data.connect;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApprovalPrompt {

    AUTO("auto"),
    FORCE("force");

    private final String value;
}
