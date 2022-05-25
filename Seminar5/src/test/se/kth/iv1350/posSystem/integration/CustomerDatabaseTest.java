package se.kth.iv1350.posSystem.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class CustomerDatabaseTest {
	private CustomerDatabase customerDatabase;

	@BeforeEach
	void setUp() {
		this.customerDatabase = CustomerDatabase.getCustomerDB();
	}

	@AfterEach
	void tearDown() {
		this.customerDatabase = null;
	}

	@Test
	void testCustomerRegistrationException() {
		String invalidCustomerID = "123";
		String expectedResult = "CustomerID '" + invalidCustomerID + "' is unregistered!";
		try {
			this.customerDatabase.getCustomerDTO(invalidCustomerID);
			fail("Retrieved non-existent customer information");
		} catch (CustomerRegistrationException exception) {
			assertEquals(expectedResult, exception.getMessage(), "Exception message mismatch!");
		}
	}
}