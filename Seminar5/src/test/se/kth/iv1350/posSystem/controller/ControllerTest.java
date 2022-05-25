package se.kth.iv1350.posSystem.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.posSystem.dto.BasketDTO;
import se.kth.iv1350.posSystem.integration.CustomerRegistrationException;
import se.kth.iv1350.posSystem.integration.ItemIdentifierException;
import se.kth.iv1350.posSystem.utilities.Amount;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ControllerTest {
	private Controller controller;

	@BeforeEach
	void setUp() {
		this.controller = new Controller();
	}

	@AfterEach
	void tearDown() {
		this.controller = null;
	}

	@Test
	void testAddItem() {
		this.controller.startSale();
		String itemIDFromItemInventory = "32784623";
		String expectedResult = itemIDFromItemInventory;
		try {
			BasketDTO basketDTO = this.controller.addItem(itemIDFromItemInventory);
			String result = basketDTO.getLastRegisteredItem().getItemID();
			assertEquals(expectedResult, result, "ReceiptDTO not generated correctly!");
		} catch (ItemIdentifierException exception) {
			fail("ItemIdentifierException incorrectly thrown!");
		} catch (Exception exception) {
			fail(exception.getClass() + "incorrectly thrown!");
		}
	}

	@Test
	void testAddItemBeforeStartSale() {
		String expectedResult = "Attempt to register item before starting a new sale.";
		try {
			this.controller.addItem("anyItemID");
			fail("Item added before new sale initiated.");
		} catch (ItemIdentifierException exception) {
			fail("ItemIdentifierException incorrectly thrown!");
		} catch (IllegalStateException exception) {
			assertEquals(expectedResult, exception.getMessage(), "Exception message mismatch!");
		}
	}

	@Test
	void testEndSaleBeforeStartingNewSale() {
		String expectedResult = "Attempt to end sale before starting a new sale.";
		try {
			this.controller.endSale();
			fail("Sale ended before initialization of a new sale.");
		} catch (IllegalStateException exception) {
			assertEquals(expectedResult, exception.getMessage(), "Exception message mismatch!");
		}
	}

	@Test
	void testItemDiscountViaRequestDiscount() {
		String validItemID = "67334553";
		String validCustomerID = "2101012031";
		Amount expectedResult = new Amount(299 * 2 - 299 * 2 * 0.2);
		this.controller.startSale();
		Amount result = new Amount(0);
		try {
			this.controller.addItem(validItemID);
			this.controller.addItem(validItemID);
		} catch (Exception exception) {
			fail("Incorrectly thrown exception!");
		}
		this.controller.endSale();
		try {
			result = this.controller.requestDiscount(validCustomerID);
		} catch (CustomerRegistrationException exception) {
			fail("CustomerRegistrationException thrown incorrectly!");
		} catch (Exception exception) {
			fail("Exception thrown incorrectly!");
		}
		assertEquals(expectedResult, result, "Item discount incorrectly calculated!");
	}

	@Test
	void testTotalDiscountViaRequestDiscount() {
		String validItemID = "95867956";
		String validCustomerID = "2101012031";
		Amount expectedResult = new Amount(1499 - 1499 * 0.05);
		this.controller.startSale();
		Amount result = new Amount(0);
		try {
			this.controller.addItem(validItemID);
		} catch (Exception exception) {
			fail("Incorrectly thrown exception!");
		}
		this.controller.endSale();
		try {
			result = this.controller.requestDiscount(validCustomerID);
		} catch (CustomerRegistrationException exception) {
			fail("CustomerRegistrationException thrown incorrectly!");
		}
		assertEquals(expectedResult, result, "Item discount incorrectly calculated!");
	}

	@Test
	void testRequestDiscountBeforeEndSale() {
		this.controller.startSale();
		String validCustomerID = "2101012031";
		String expectedResult = "Attempt to request discount before starting ending sale.";
		try {
			this.controller.requestDiscount(validCustomerID);
		} catch (CustomerRegistrationException exception) {
			fail("CustomerRegistrationException thrown incorrectly!");
		} catch (IllegalStateException exception) {
			assertEquals(expectedResult, exception.getMessage(), "Exception message mismatch!");
		}
	}

	@Test
	void testRegisterPaymentBeforeEndSale() {
		double amountPaidInCash = 100;
		this.controller.startSale();
		String expectedResult = "Attempt to register payment before ending sale.";
		try {
			this.controller.registerPayment(amountPaidInCash);
		} catch (IllegalStateException exception) {
			assertEquals(expectedResult, exception.getMessage(), "Exception message mismatch!");
		}
	}
}