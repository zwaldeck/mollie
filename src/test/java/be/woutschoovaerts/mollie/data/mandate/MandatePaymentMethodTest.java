package be.woutschoovaerts.mollie.data.mandate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MandatePaymentMethodTest {

    @Test
    void testDirectDebitJsonValue() {
        assertEquals("directdebit", MandatePaymentMethod.DIRECTDEBIT.getJsonValue());
    }

    @Test
    void testCreditCardJsonValue() {
        assertEquals("creditcard", MandatePaymentMethod.CREDITCARD.getJsonValue());
    }

    @Test
    void testPaypalJsonValue() {
        assertEquals("paypal", MandatePaymentMethod.PAYPAL.getJsonValue());
    }

    @Test
    void testBancontactJsonValue() {
        assertEquals("bancontact", MandatePaymentMethod.BANCONTACT.getJsonValue());
    }

}
