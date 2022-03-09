package be.woutschoovaerts.mollie.util;

import be.woutschoovaerts.mollie.data.common.Amount;
import com.fasterxml.jackson.annotation.JsonValue;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

public interface QueryParamMapper {

    /**
     * Generate the query string from all the [key, value] pairs
     *
     * @return the query string starting with ?, or empty string if none
     */
    default String toQueryParams() {

        StringJoiner sj = new StringJoiner("&", "?", "")
                .setEmptyValue("");

        final Field[] fields = getClass().getDeclaredFields();
        for (Field field : fields) {
            final boolean accessible = field.canAccess(this);
            try {
                field.setAccessible(true);
                Object value = field.get(this);
                if (value instanceof Optional) {
                    value = ((Optional<?>) value).orElse(null);
                }
                if (value instanceof List && ((List<?>) value).isEmpty()) {
                    value = null;
                }
                if (value != null) {
                    if (value instanceof Amount) {
                        final Field[] innerFields = value.getClass().getDeclaredFields();
                        for (Field innerField : innerFields) {
                            final boolean innerAccessible = innerField.canAccess(value);
                            try {
                                innerField.setAccessible(true);
                                final Object innerValue = innerField.get(value);
                                if (innerValue != null) {
                                    sj.add(UrlUtils.urlEncode(field.getName() + "[" + innerField.getName() + "]") +
                                            "=" + UrlUtils.urlEncode(innerValue.toString()));
                                }
                            } catch (IllegalAccessException ignored) {
                            } finally {
                                innerField.setAccessible(innerAccessible);
                            }
                        }
                    } else {
                        String innerValue = value.toString();
                        if (value.getClass().isEnum()) {
                            try {
                                Method[] innerField = value.getClass().getDeclaredMethods();
                                for (Method method : innerField) {
                                    if (method.isAnnotationPresent(JsonValue.class)) {
                                        innerValue = method.invoke(value).toString();
                                        break;
                                    }
                                }
                            } catch (IllegalAccessException | InvocationTargetException ignored) {
                            }
                        }
                        sj.add(UrlUtils.urlEncode(field.getName()) + "=" + UrlUtils.urlEncode(innerValue));
                    }
                }
            } catch (IllegalAccessException ignored) {
            } finally {
                field.setAccessible(accessible);
            }
        }

        return sj.toString();
    }
}
