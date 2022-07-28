package be.woutschoovaerts.mollie.data.chargeback;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

import be.woutschoovaerts.mollie.util.ObjectMapperService;
import com.fasterxml.jackson.core.type.TypeReference;

class ChargebackResponseTest {

	@Test
	public void testChargebackWithReason() throws IOException {
		ChargebackResponse cb = parse("chargeback-with-reason.json");
		assertEquals("AM04", cb.getReason().get().getCode(), "reason.code");
		assertEquals("Insufficient funds", cb.getReason().get().getDescription(), "reason.description");
	}
	
	@Test
	public void testChargebackWithoutReason() throws IOException {
		ChargebackResponse cb = parse("chargeback-without-reason.json");
		assertFalse(cb.getReason().isPresent(), "reason");
	}
	
	private ChargebackResponse parse(String resourcename) throws IOException {
		try (InputStream is = getClass().getResourceAsStream(resourcename)) {
			assertNotNull(is, "resource " + resourcename + " not found");
			return ObjectMapperService.getInstance().getMapper().readValue(is,
                    new TypeReference<ChargebackResponse>() {
                    });
		}
	}

}
