package se.kth.iv1350.posSystem.dto;

import se.kth.iv1350.posSystem.utilities.Amount;

/**
 * Represents an item which may be included in a sale
 */
public class ItemDTO {
    private final String itemID;
    private final String itemDescription;
    private final Amount itemPrice;
    private final Amount itemVATRate;

    /**
     * Creates an instance of an item.
     *
     * @param itemID          The unique item identifier
     * @param itemDescription The description of the item
     * @param itemPrice       The price of the item
     * @param itemVATRate     The VAT rate of the item
     */
    public ItemDTO(String itemID, String itemDescription, Amount itemPrice, Amount itemVATRate) {
        this.itemID = itemID;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.itemVATRate = itemVATRate;
    }

    /**
     * Gets the item identifier of the item
     *
     * @return The item identifier
     */
    public String getItemID() {
        return this.itemID;
    }

    /**
     * Gets the VAT rate of the item
     *
     * @return The item VAT rate
     */
    public Amount getItemVATRate() {
        return this.itemVATRate;
    }

    /**
     * Generates a printable output of an item
     */
    public String toString() {
        return getItemDescription() + "\t\t" + getItemPrice();
    }

    /**
     * Gets the item description of the item
     *
     * @return The item description
     */
    public String getItemDescription() {
        return this.itemDescription;
    }

    /**
     * Gets the price of the item
     *
     * @return The item price
     */
    public Amount getItemPrice() {
        return this.itemPrice;
    }
}
