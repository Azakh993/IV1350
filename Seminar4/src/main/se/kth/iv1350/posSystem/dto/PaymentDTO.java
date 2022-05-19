package se.kth.iv1350.posSystem.dto;

import se.kth.iv1350.posSystem.utilities.Amount;

/**
 * Represents the receipt data of a sale
 */
public class PaymentDTO {

    private final Amount totalPrice;
    private final Amount totalVAT;
    private final Amount discount;
    private final Amount totalPriceAfterDiscount;
    private final Amount totalVATAfterDiscount;
    private final Amount amountPaid;
    private final Amount change;

    /**
     * Creates a new instance of <code>PaymentDTO</code> containing payment data
     * Used for transactions where discount has not been requested.
     *
     * @param totalPrice The total price for the items bought
     * @param totalVAT   The total VAT for the items bought
     * @param amountPaid The amount paid in cash
     * @param change     The amount of change to give back
     */
    public PaymentDTO(Amount totalPrice, Amount totalVAT, Amount amountPaid, Amount change) {
        this.totalPrice = totalPrice;
        this.totalVAT = totalVAT;
        this.discount = null;
        this.totalPriceAfterDiscount = null;
        this.totalVATAfterDiscount = null;
        this.amountPaid = amountPaid;
        this.change = change;
    }

    /**
     * Creates a new instance of <code>PaymentDTO</code> containing payment data
     * Used for transaction where discount has been requested
     *
     * @param totalPrice              The total price for the items bought
     * @param totalVAT                The total VAT for the items bought
     * @param amountPaid              The amount paid in cash
     * @param change                  The amount of change to give back
     * @param discount                The discount applied to the sale / null if none is applied
     * @param totalPriceAfterDiscount The total price after discount has been applied / null if none is applied
     * @param totalVATAfterDiscount   The total VAT after discount has been applied / null if none is applied
     */
    public PaymentDTO(Amount totalPrice, Amount totalVAT, Amount amountPaid, Amount change,
                      Amount discount, Amount totalPriceAfterDiscount, Amount totalVATAfterDiscount) {
        this.totalPrice = totalPrice;
        this.totalVAT = totalVAT;
        this.discount = discount;
        this.totalPriceAfterDiscount = totalPriceAfterDiscount;
        this.totalVATAfterDiscount = totalVATAfterDiscount;
        this.amountPaid = amountPaid;
        this.change = change;
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

    public Amount getDiscount() {
        return discount;
    }

    public Amount getTotalPriceAfterDiscount() {
        return totalPriceAfterDiscount;
    }

    public Amount getTotalVATAfterDiscount() {
        return totalVATAfterDiscount;
    }
}

