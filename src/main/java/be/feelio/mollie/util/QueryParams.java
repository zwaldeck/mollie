package be.feelio.mollie.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

public class QueryParams extends LinkedHashMap<String, String> {

    public static final QueryParams EMPTY = new QueryParams();

    public String toString() {
        if (size() == 0) {
            return "";
        }

        StringJoiner sj = new StringJoiner("&", "?", "");
        for (Map.Entry<String, String> entry : this.entrySet()) {
            sj.add(entry.getKey() + "=" + entry.getValue());
        }

        return sj.toString();
    }
}
