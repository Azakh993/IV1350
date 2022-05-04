package se.kth.iv1350.posSystem.controller;

import org.junit.jupiter.api.*;
import se.kth.iv1350.posSystem.integration.ItemDTO;
import se.kth.iv1350.posSystem.integration.SystemHandler;
import se.kth.iv1350.posSystem.model.Basket;
import se.kth.iv1350.posSystem.model.Payment;
import se.kth.iv1350.posSystem.model.SaleDTO;
import se.kth.iv1350.posSystem.utilities.Amount;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private Controller controller;
    private SystemHandler systemHandler;
    private SaleDTO saleDTO;

    @BeforeEach
    void setUp() {
        this.controller = new Controller();
        this.controller.startSale();
    }

    @AfterEach
    void tearDown() {
        this.controller = null;
        this.saleDTO = null;
    }

    @Test
    void testUpdateSaleDTOViaAddItem() {
        String itemIDFromItemInventory = "32784623";
        this.saleDTO = this.controller.addItem(itemIDFromItemInventory);
        boolean expectedResult = true;
        boolean result = this.saleDTO != null;
        assertEquals(expectedResult, result, "SaleDTO not generated correctly!");
    }

    @Test
    void testSaleDTOBasketViaAddItem() {
        String itemIDFromItemInventory = "32784623";
        this.saleDTO = this.controller.addItem(itemIDFromItemInventory);
        String expectedItemIDListing = itemIDFromItemInventory;
        String itemIDListing = this.saleDTO.getLastRegisteredItem().getItemID();
        Amount expectedItemQty = new Amount(1);
        Amount itemQty = this.saleDTO.getItemsInBasket().get(this.saleDTO.getLastRegisteredItem());
        assertEquals(expectedItemIDListing, itemIDListing, "Item not added to Basket in SaleDTO!");
        assertEquals(expectedItemQty, itemQty, "Item not added to Basket in SaleDTO!");
    }

    @Test
    void testSaleDTOTotalPriceViaAddItem() {
        String itemIDFromItemInventory = "32784623";
        this.saleDTO = this.controller.addItem(itemIDFromItemInventory);
        Amount expectedTotalPrice = new Amount(99);
        Amount totalPrice = this.saleDTO.getTotalPrice();
        assertEquals(expectedTotalPrice, totalPrice, "Total Price set incorrectly!");
    }

    @Test
    void testSaleDTOTotalVATViaAddItem() {
        String itemIDFromItemInventory = "32784623";
        this.saleDTO = this.controller.addItem(itemIDFromItemInventory);
        Amount expectedTotalVAT = new Amount(99 * 0.25);
        Amount totalVAT = this.saleDTO.getTotalVAT();
        assertEquals(expectedTotalVAT, totalVAT, "Total Price set incorrectly!");
    }

    @Test
    void testUpdateAmountPaidAndChangeViaRegisterPayment() {
        String itemIDFromItemInventory = "32784623";
        this.saleDTO = this.controller.addItem(itemIDFromItemInventory);
        double amountPaidInCash = 100;
        this.saleDTO = this.controller.registerPayment(amountPaidInCash);
        Amount expectedAmountPaid = new Amount(100);
        Amount amountPaid = this.saleDTO.getAmountPaid();
        Amount expectedChange = new Amount(100 - 99);
        Amount amountChange = this.saleDTO.getChange();
        assertEquals(expectedAmountPaid, amountPaid, "SaleDTO not generated correctly!");
        assertEquals(expectedChange, amountChange, "SaleDTO not generated correctly!");
    }
}