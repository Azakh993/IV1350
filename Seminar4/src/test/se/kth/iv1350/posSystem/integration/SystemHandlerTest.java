package se.kth.iv1350.posSystem.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.posSystem.model.Basket;
import se.kth.iv1350.posSystem.model.ItemDTO;
import se.kth.iv1350.posSystem.model.Payment;
import se.kth.iv1350.posSystem.model.SaleDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class SystemHandlerTest {
    private SystemHandler systemHandler;
    private Basket basket;
    private Payment payment;

    @BeforeEach
    void setUp() {
        this.systemHandler = new SystemHandler();
        this.basket = new Basket();
        this.payment = new Payment();
    }

    @AfterEach
    void tearDown() {
        this.systemHandler = null;
        this.basket = null;
        this.payment = null;
    }

    @Test
    void testFetchItemWithInvalidItemIdentifier() {
        String invalidItemID = "ABC";
        String expectedResult = "Item identifier '" + invalidItemID + "' not found in item catalogue!";

        try {
            this.systemHandler.fetchItem(invalidItemID);
            fail("Non-existing item was retrieved from External Inventory System");
        } catch (ExternalSystemException exception) {
            fail("ExternalSystemException triggered incorrectly.");
        } catch (ItemIdentifierException exception) {
            assertEquals(expectedResult, exception.getMessage());
        }
    }

    @Test
    void testFetchItemWithoutExternalInventorySystemConnection() {
        String connectionFailureSimulatingItemID = "404";
        String expectedResult = "Could not establish contact with 'ExternalInventorySystem'!";

        try {
            this.systemHandler.fetchItem(connectionFailureSimulatingItemID);
            fail("Connected to External Inventory System when it is inaccessible.");
        } catch (ItemIdentifierException exception) {
            fail("ExternalSystemException not triggered correctly.");
        } catch (ExternalSystemException exception) {
            assertEquals(expectedResult, exception.getMessage(), "Exception message mismatch.");
        }
    }

    @Test
    void testFetchItem() {
        String validItemID = "95867956";
        String expectedResult = "95867956";

        try {
            String result = this.systemHandler.fetchItem(validItemID).getItemID();
            assertEquals(expectedResult, result, "Incorrect item fetched from external system.");
        } catch (ItemIdentifierException exception) {
            fail("ItemIdentifierException thrown incorrectly.");
        } catch (ExternalSystemException exception) {
            fail("ExternalSystemException thrown incorrectly.");
        }
    }

    @Test
    void testPrintReceiptWithoutPrinterConnection() {
        String connectionFailureSimulatingItemID = "404X";
        String expectedResult = "Could not establish contact with 'ReceiptPrinter'!";

        try {
            ItemDTO testItemDTO = this.systemHandler.fetchItem(connectionFailureSimulatingItemID);
            this.basket.setItemInBasket(testItemDTO);
            SaleDTO saleDTO = new SaleDTO(this.payment, this.basket);
            this.systemHandler.printReceipt(saleDTO);
            fail("ReceiptPrinter printed receipt without a functioning connection.");
        } catch (ItemIdentifierException exception) {
            fail("ItemIdentifierException incorrectly thrown; item is valid item.");
        } catch (ExternalSystemException exception) {
            assertEquals(expectedResult, exception.getMessage(), "Exception message mismatch.");
        }
    }

    @Test
    void testPrintReceipt() {
        String validItemID = "95867956";

        try {
            ItemDTO testItemDTO = this.systemHandler.fetchItem(validItemID);
            this.basket.setItemInBasket(testItemDTO);
            SaleDTO saleDTO = new SaleDTO(this.payment, this.basket);
            this.systemHandler.printReceipt(saleDTO);
        } catch (ItemIdentifierException exception) {
            fail("ItemIdentifierException incorrectly thrown; item is valid item.");
        } catch (ExternalSystemException exception) {
            fail("ExternalSystemException incorrectly thrown; ReceiptPrinter is connected.");
        }
    }
}