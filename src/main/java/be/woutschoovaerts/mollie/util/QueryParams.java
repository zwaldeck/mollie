package be.woutschoovaerts.mollie.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

public class QueryParams extends LinkedHashMap<String, String> {

    /**
     * Generate the query string from all the [key, value] pairs
     *
     * @return the query string starting with ?
     */
    public String toString() {
        if (size() == 0) {
            return "";
        }

        StringJoiner sj = new StringJoiner("&", "?", "");
        for (Map.Entry<String, String> entry : this.entrySet()) {
            sj.add(UrlUtils.urlEncode(entry.getKey()) + "=" + UrlUtils.urlEncode(entry.getValue()));
        }

        return sj.toString();
    }
}
