package be.woutschoovaerts.mollie.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class MollieException extends Exception {


    private Map<String, Object> details;

    public MollieException(String message, Map<String, Object> details) {
        super(message);
        this.details = details;
    }

    public MollieException(Throwable cause) {
        super(cause);
        details = new HashMap<>();
    }
}
