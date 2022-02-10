package be.woutschoovaerts.mollie.util;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public final class UrlUtils {

    private UrlUtils() {
    }

    public static String urlEncode(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }
}
