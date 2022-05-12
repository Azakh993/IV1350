package se.kth.iv1350.posSystem.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.posSystem.utilities.Amount;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketTest {
    private LinkedHashMap<ItemDTO, Amount> itemsInBasket = new LinkedHashMap<>();
    private Basket basket;
    private ItemDTO defaultItemInBasket;
    private Amount defaultItemQty;

    @BeforeEach
    void setUp() {
        this.defaultItemInBasket = new ItemDTO("i1", "Book",
                new Amount(149), new Amount(0.06));
        this.defaultItemQty = new Amount(1);
        this.itemsInBasket.put(defaultItemInBasket, defaultItemQty);

        this.basket = new Basket();
        this.basket.setItemInBasket(defaultItemInBasket);
    }

    @AfterEach
    void tearDown() {
        this.itemsInBasket = null;
        this.basket = null;
        this.defaultItemInBasket = null;
        this.defaultItemQty = null;
    }

    @Test
    void testIncrementItemInBasketViaSetItemInBasket() {
        Amount incrementedAmount = new Amount(2);
        this.basket.setItemInBasket(defaultItemInBasket);
        Amount expectedResult = incrementedAmount;
        Amount result = basket.getItemsInBasket().get(defaultItemInBasket);
        assertEquals(expectedResult, result, "Item in basket was not incremented!");
    }

    @Test
    void testSetNewItemInBasketViaSetItemInBasket() {
        ItemDTO newItem = new ItemDTO("i2", "Cable", new Amount(199), new Amount(0.25));
        this.basket.setItemInBasket(newItem);
        boolean expectedItemListing = true;
        boolean itemListing = this.basket.getItemsInBasket().containsKey(newItem);
        Amount expectedItemQty = defaultItemQty;
        Amount itemQty = this.basket.getItemsInBasket().get(newItem);
        assertEquals(expectedItemListing, itemListing, "New item was not added to basket!");
        assertEquals(expectedItemQty, itemQty, "Item in basket was not incremented!");
    }

    @Test
    void testSetTotalPriceAndTotalVATViaSetItemInBasket() {
        ItemDTO newItem = new ItemDTO("i2", "Cable", new Amount(199), new Amount(0.25));
        this.basket.setItemInBasket(newItem);
        Amount expectedTotalPrice = new Amount(199 + 149);
        Amount totalPrice = this.basket.getTotalPrice();
        Amount expectedTotalVAT = new Amount(199 * 0.25 + 149 * 0.06);
        Amount totalVAT = this.basket.getTotalVAT();
        assertEquals(expectedTotalPrice, totalPrice, "Total price is set incorrectly!");
        assertEquals(expectedTotalVAT, totalVAT, "Total VAT is set incorrectly!");
    }
}