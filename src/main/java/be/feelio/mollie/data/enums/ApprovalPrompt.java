package be.feelio.mollie.data.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApprovalPrompt {

    AUTO("auto"),
    FORCE("force");

    private final String value;
}
