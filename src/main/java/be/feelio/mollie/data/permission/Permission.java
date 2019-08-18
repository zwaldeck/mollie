package be.feelio.mollie.data.permission;

import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang3.StringUtils;

public enum Permission {

    BALANCE_REPORTS_READ,
    APIKEYS_READ,
    APIKEYS_WRITE,
    BALANCES_READ,
    BALANCES_WRITE,
    BANKACCOUNTS_READ,
    BANKACCOUNTS_WRITE,
    MEMBERSHIPS_READ,
    TRANSFERS_READ,
    TRANSFERS_WRITE,
    PAYMENTS_READ,
    PAYMENTS_WRITE,
    REFUNDS_READ,
    REFUNDS_WRITE,
    CUSTOMERS_READ,
    CUSTOMERS_WRITE,
    MANDATES_READ,
    MANDATES_WRITE,
    SUBSCRIPTIONS_READ,
    SUBSCRIPTIONS_WRITE,
    PROFILES_READ,
    PROFILES_WRITE,
    INVOICES_READ,
    SETTLEMENTS_READ,
    ORDERS_READ,
    ORDERS_WRITE,
    SHIPMENTS_READ,
    SHIPMENTS_WRITE,
    ORGANIZATIONS_READ,
    ORGANIZATIONS_WRITE,
    ONBOARDING_READ,
    ONBOARDING_WRITE;


    @JsonValue
    public String getValue() {
        String result = StringUtils.reverse(this.name().toLowerCase());
        result = result.replaceFirst("_", ".");
        result = StringUtils.reverse(result);
        return result.replace("_", ".");
    }
}
