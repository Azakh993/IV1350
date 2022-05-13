package se.kth.iv1350.posSystem.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.posSystem.integration.ItemIdentifierException;
import se.kth.iv1350.posSystem.integration.SystemHandler;
import se.kth.iv1350.posSystem.model.SaleDTO;
import se.kth.iv1350.posSystem.utilities.Amount;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ControllerTest {
    private Controller controller;
    private SaleDTO saleDTO;

    @BeforeEach
    void setUp() {
        this.controller = new Controller();
    }

    @AfterEach
    void tearDown() {
        this.controller = null;
    }

    @Test
    void testUpdateSaleDTOViaAddItem() {
        this.controller.startSale();
        String itemIDFromItemInventory = "32784623";
        try {
            this.saleDTO = this.controller.addItem(itemIDFromItemInventory);
            boolean expectedResult = true;
            boolean result = this.saleDTO != null;
            assertEquals(expectedResult, result, "SaleDTO not generated correctly!");
        } catch (ItemIdentifierException exception) {
            fail("ItemIdentifierException incorrectly thrown!");
        } catch (OperationFailedException exception) {
            fail("OperationFailedException incorrectly thrown!");
        }
    }

    @Test
    void testSaleDTOBasketViaAddItem() {
        this.controller.startSale();
        String itemIDFromItemInventory = "32784623";
        try {
            this.saleDTO = this.controller.addItem(itemIDFromItemInventory);
            String expectedItemIDListing = itemIDFromItemInventory;
            String itemIDListing = this.saleDTO.getLastRegisteredItem().getItemID();
            Amount expectedItemQty = new Amount(1);
            Amount itemQty = this.saleDTO.getItemsInBasket().get(this.saleDTO.getLastRegisteredItem());
            assertEquals(expectedItemIDListing, itemIDListing, "Item not added to Basket in SaleDTO!");
            assertEquals(expectedItemQty, itemQty, "Item not added to Basket in SaleDTO!");
        } catch (ItemIdentifierException exception) {
            fail("ItemIdentifierException incorrectly thrown!");
        } catch (OperationFailedException exception) {
            fail("OperationFailedException incorrectly thrown!");
        }
    }

    @Test
    void testSaleDTOTotalPriceViaAddItem() {
        this.controller.startSale();
        String itemIDFromItemInventory = "32784623";
        try {
            this.saleDTO = this.controller.addItem(itemIDFromItemInventory);
            Amount expectedTotalPrice = new Amount(99);
            Amount totalPrice = this.saleDTO.getTotalPrice();
            assertEquals(expectedTotalPrice, totalPrice, "Total Price set incorrectly!");
        } catch (ItemIdentifierException exception) {
            fail("ItemIdentifierException incorrectly thrown!");
        } catch (OperationFailedException exception) {
            fail("OperationFailedException incorrectly thrown!");
        }
    }

    @Test
    void testSaleDTOTotalVATViaAddItem() {
        this.controller.startSale();
        String itemIDFromItemInventory = "32784623";
        try {
            this.saleDTO = this.controller.addItem(itemIDFromItemInventory);
            Amount expectedTotalVAT = new Amount(99 * 0.25);
            Amount totalVAT = this.saleDTO.getTotalVAT();
            assertEquals(expectedTotalVAT, totalVAT, "Total Price set incorrectly!");
        } catch (ItemIdentifierException exception) {
            fail("ItemIdentifierException incorrectly thrown!");
        } catch (OperationFailedException exception) {
            fail("OperationFailedException incorrectly thrown!");
        }
    }
    @Test
    void testAddItemBeforeStartSale() {
        String invalidItemID = "32784623";
        String expectedResult = "Attempt to register item before starting a new sale.";
        try {
            this.saleDTO = this.controller.addItem(invalidItemID);
            fail("Item added before new sale initiated.");
        } catch (ItemIdentifierException exception) {
            fail("ItemIdentifierException incorrectly thrown!");
        } catch (OperationFailedException exception) {
            fail("OperationFailedException incorrectly thrown!");
        } catch (IllegalStateException exception) {
            assertEquals(expectedResult, exception.getMessage(), "Exception message mismatch!");
        }
    }

    @Test
    void testEndSaleBeforeStartingNewSale() {
        String expectedResult = "Attempt to end sale before starting a new sale.";
        try {
            this.saleDTO = this.controller.endSale();
            fail("Sale ended before initialization of a new sale.");
        } catch (IllegalStateException exception) {
            assertEquals(expectedResult, exception.getMessage(), "Exception message mismatch!");
        }
    }

    @Test
    void testUpdateAmountPaidAndChangeViaRegisterPayment() {
        this.controller.startSale();
        String itemIDFromItemInventory = "32784623";
        try {
            this.saleDTO = this.controller.addItem(itemIDFromItemInventory);
            double amountPaidInCash = 100;
            this.saleDTO = this.controller.registerPayment(amountPaidInCash);
            Amount expectedAmountPaid = new Amount(100);
            Amount amountPaid = this.saleDTO.getAmountPaid();
            Amount expectedChange = new Amount(100 - 99);
            Amount amountChange = this.saleDTO.getChange();
            assertEquals(expectedAmountPaid, amountPaid, "SaleDTO not generated correctly!");
            assertEquals(expectedChange, amountChange, "SaleDTO not generated correctly!");
        } catch (ItemIdentifierException exception) {
            fail("ItemIdentifierException incorrectly thrown!");
        } catch (OperationFailedException exception) {
            fail("OperationFailedException incorrectly thrown!");
        }
    }

    @Test
    void testRegisterPaymentBeforeStartingNewSale() {
        double amountPaidInCash = 100;
        String expectedResult = "Attempt to register payment before starting new sale.";
        try {
            this.saleDTO = this.controller.registerPayment(amountPaidInCash);
        } catch (IllegalStateException exception) {
            assertEquals(expectedResult, exception.getMessage(), "Exception message mismatch!");
        }
    }
}