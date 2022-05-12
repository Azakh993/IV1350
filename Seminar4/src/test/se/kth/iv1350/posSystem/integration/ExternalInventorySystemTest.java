package se.kth.iv1350.posSystem.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.posSystem.model.Basket;
import se.kth.iv1350.posSystem.model.ItemDTO;
import se.kth.iv1350.posSystem.model.Payment;
import se.kth.iv1350.posSystem.model.SaleDTO;
import se.kth.iv1350.posSystem.utilities.Amount;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ExternalInventorySystemTest {
    private ExternalInventorySystem externalInventorySystem;
    private Basket basket;
    private Payment payment;

    @BeforeEach
    void setUp() {
        this.externalInventorySystem = new ExternalInventorySystem();
        this.basket = new Basket();
        this.payment = new Payment();
    }

    @AfterEach
    void tearDown() {
        this.externalInventorySystem = null;
        this.basket = null;
        this.payment = null;
    }

    @Test
    void testSetItemInventory() {
        ItemDTO testItemDTO = new ItemDTO("95867956", "Apple Keyboard",
                new Amount(1499), new Amount(0.25));
        this.basket.setItemInBasket(testItemDTO);
        SaleDTO saleDTO = new SaleDTO(this.payment, this.basket);
        this.externalInventorySystem.setItemInventory(saleDTO);
        Amount expectedItemQtyInInventory = new Amount(0);
        Amount itemQtyInInventory = this.externalInventorySystem.getItemInventory().get("95867956");
        assertEquals(expectedItemQtyInInventory, itemQtyInInventory, "Item quantity not set correctly!");
    }

    @Test
    void testSetItemInventorySoStockGetsNegative() {
        ItemDTO testItemDTO = new ItemDTO("95867956", "Apple Keyboard",
                new Amount(1499), new Amount(0.25));
        this.basket.setItemInBasket(testItemDTO);
        this.basket.setItemInBasket(testItemDTO);
        SaleDTO saleDTO = new SaleDTO(this.payment, this.basket);
        this.externalInventorySystem.setItemInventory(saleDTO);
        Amount expectedItemQtyInInventory = new Amount(-1);
        Amount itemQtyInInventory = this.externalInventorySystem.getItemInventory().get("95867956");
        assertEquals(expectedItemQtyInInventory, itemQtyInInventory, "Item quantity not set correctly!");
    }

    @Test
    void testGetItemWithInvalidItemIdentifier() {
        String invalidItemID = "ABC";
        String expectedResult = "Item identifier '" + invalidItemID + "' not found in item catalogue!";

        try {
            this.externalInventorySystem.getItem(invalidItemID);
            fail("Non-existing item was retrieved from External Inventory System");
        } catch (InvalidItemIdentifierException exception) {
            assertEquals(expectedResult, exception.getMessage());
        }
    }
}