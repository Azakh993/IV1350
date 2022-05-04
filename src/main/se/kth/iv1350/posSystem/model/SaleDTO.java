package se.kth.iv1350.posSystem.model;

import se.kth.iv1350.posSystem.utilities.Amount;

import java.util.LinkedHashMap;

/**
 * Represents the receipt data of a sale
 */
public class SaleDTO {

    private final String timeAndDateOfSale;
    private final LinkedHashMap<ItemDTO, Amount> itemsInBasket;
    private final ItemDTO lastRegisteredItem;
    private final Amount totalPrice;
    private final Amount totalVAT;
    private final Amount amountPaid;
    private final Amount change;

    /**
     * Creates a new instance of SaleDTO
     *
     * @param payment The object containing payment data
     * @param basket  The object containing items in basket
     */
    public SaleDTO(Payment payment, Basket basket) {
        this.timeAndDateOfSale = payment.getTimeAndDateOfSale();
        this.itemsInBasket = basket.getItemsInBasket();
        this.lastRegisteredItem = basket.getLastRegisteredItem();
        this.totalPrice = basket.getTotalPrice();
        this.totalVAT = basket.getTotalVAT();
        this.amountPaid = payment.getAmountPaid();
        this.change = payment.getChange();
    }

    /**
     * Gets the time and date of the sale
     *
     * @return The time and date of the sale
     */
    public String getTimeAndDateOfSale() {
        return this.timeAndDateOfSale;
    }

    /**
     * Gets the items in the basket of a sale
     *
     * @return The LinkedHashMap of items and their quantity
     */
    public LinkedHashMap<ItemDTO, Amount> getItemsInBasket() {
        return this.itemsInBasket;
    }

    public ItemDTO getLastRegisteredItem() {
        return this.lastRegisteredItem;
    }

    /**
     * Gets the running total / total price of the sale
     *
     * @return The running total / total price
     */
    public Amount getTotalPrice() {
        return this.totalPrice;
    }

    /**
     * Gets the total VAT of the sale
     *
     * @return The total VAT
     */
    public Amount getTotalVAT() {
        return this.totalVAT;
    }

    /**
     * Gets the amount paid by the customer
     *
     * @return The amount paid in cash
     */
    public Amount getAmountPaid() {
        return this.amountPaid;
    }

    /**
     * Gets the change to give to customer
     *
     * @return The change in cash
     */
    public Amount getChange() {
        return this.change;
    }

    /**
     * Generates a textual representation of the object instance
     *
     * @return String representation of SaleDTO instance
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        stringBuilder.append("\t\tRECEIPT\n\n");
        stringBuilder.append(getTimeAndDateOfSale());
        stringBuilder.append("\n\n");

        for (ItemDTO item : this.itemsInBasket.keySet()) {
            stringBuilder.append(String.format("%.0f", this.itemsInBasket.get(item).getAmount()));
            stringBuilder.append("*\t");
            stringBuilder.append(item.toString());
            stringBuilder.append("\n");
        }

        stringBuilder.append("\nTotal price: ");
        stringBuilder.append(getTotalPrice());
        stringBuilder.append("\n");

        stringBuilder.append("Total VAT: ");
        stringBuilder.append(getTotalVAT());
        stringBuilder.append("\n");

        stringBuilder.append("Paid: ");
        stringBuilder.append(getAmountPaid());
        stringBuilder.append("\n");

        stringBuilder.append("Change ");
        stringBuilder.append(getChange());
        stringBuilder.append("\n\n");

        stringBuilder.append("\tEND OF RECEIPT\n\n");

        return stringBuilder.toString();
    }
}

