package be.woutschoovaerts.mollie.data.chargeback;

import be.woutschoovaerts.mollie.util.ObjectMapperService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class ChargebackResponseTest {

	@Test
	public void testChargebackWithReason() throws IOException {
		ChargebackResponse cb = parse("chargeback/with-reason.json");
		assertEquals("AM04", cb.getReason().getCode(), "reason.code");
		assertEquals("Insufficient funds", cb.getReason().getDescription(), "reason.description");
	}
	
	@Test
	public void testChargebackWithoutReason() throws IOException {
		ChargebackResponse cb = parse("chargeback/without-reason.json");
		assertNull(cb.getReason(), "reason");
	}

	private ChargebackResponse parse(String resourcename) throws IOException {
		try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourcename)) {
			assertNotNull(is, "resource " + resourcename + " not found");
			return ObjectMapperService.getInstance().getMapper().readValue(is,
                    new TypeReference<ChargebackResponse>() {
                    });
		}
	}

}
