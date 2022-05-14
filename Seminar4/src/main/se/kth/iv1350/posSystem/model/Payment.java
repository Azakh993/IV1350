package se.kth.iv1350.posSystem.model;

import se.kth.iv1350.posSystem.utilities.Amount;
import se.kth.iv1350.posSystem.utilities.TimeAndDate;

/**
 * Manages payment aspects of a sale instance
 */
public class Payment {
    private TimeAndDate timeAndDateOfSale;
    private Amount amountPaid;
    private Amount change;

    /**
     * Sets the amount paid and the change, based on the final price of the sale
     *
     * @param amountPaid  The amount paid in cash
     * @param amountToPay The total price for all items in basket
     */
    public void setAmountPaidAndChange(Amount amountPaid, Amount amountToPay) {
        this.setAmountPaid(amountPaid);
        this.setChange(amountToPay);
        timeAndDateOfSale = new TimeAndDate();
    }

    TimeAndDate getTimeAndDateOfSale() {
        return this.timeAndDateOfSale;
    }

    Amount getAmountPaid() {
        return this.amountPaid;
    }

    void setAmountPaid(Amount amountPaid) {
        this.amountPaid = amountPaid;
    }

    Amount getChange() {
        return this.change;
    }

    void setChange(Amount amountToPay) {
        this.change = this.amountPaid.minus(amountToPay);
    }
}