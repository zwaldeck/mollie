package be.feelio.mollie.data.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Permission {

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
        return this.name().toLowerCase().replace("_", ".");
    }
}
