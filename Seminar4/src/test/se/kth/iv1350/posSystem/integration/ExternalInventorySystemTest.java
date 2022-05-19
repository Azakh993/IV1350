package se.kth.iv1350.posSystem.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.posSystem.dto.ItemDTO;
import se.kth.iv1350.posSystem.controller.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ExternalInventorySystemTest {
    private ExternalInventorySystem externalInventorySystem;
    private Controller controller;

    @BeforeEach
    void setUp() {
        this.externalInventorySystem = ExternalInventorySystem.getExternalInventorySystem();
        this.controller = new Controller();
    }

    @AfterEach
    void tearDown() {
        this.externalInventorySystem = null;
        this.controller = null;
    }

    @Test
    void testSetItemInventory() {
        String validItemID = "95867956";
        double expectedResult = this.externalInventorySystem.getItemInventory().get(validItemID).getAmount() - 2;
        this.controller.startSale();
        try {
            this.controller.addItem(validItemID);
            this.controller.addItem(validItemID);
        } catch (Exception exception) {
            fail("Exception incorrectly thrown!");
        }
        this.controller.endSale();
        double amountPaid = 3000;
        this.controller.registerPayment(amountPaid);
        double result = this.externalInventorySystem.getItemInventory().get(validItemID).getAmount();
        assertEquals(expectedResult, result, "Item quantity is not set correctly!");
    }

    @Test
    void testGetItem() {
        String validItemID = "95867956";
        ItemDTO expectedResult = this.externalInventorySystem.getItemCatalogue().get(validItemID);
        this.controller.startSale();
        try {
            ItemDTO result = this.externalInventorySystem.getItem(validItemID);
            assertEquals(expectedResult, result, "Item quantity is not set correctly!");
        } catch (Exception exception) {
            fail("Exception incorrectly thrown!");
        }
    }
}