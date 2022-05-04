package se.kth.iv1350.posSystem.model;

import se.kth.iv1350.posSystem.utilities.Amount;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Manages payment aspects of a sale instance
 */
public class Payment {
    private String timeAndDateOfSale;
    private Amount amountPaid;
    private Amount change;

    /**
     * Creates a new Payment object
     * Registers time of creation
     */
    public Payment() {
        setTimeAndDateOfSale();
    }

    /**
     * Sets the amount paid and the change, based on the final price of the sale
     *
     * @param amountPaid  The amount paid in cash
     * @param amountToPay The total price for all items in basket
     */
    public void setAmountPaidAndChange(Amount amountPaid, Amount amountToPay) {
        this.setAmountPaid(amountPaid);
        this.setChange(amountToPay);
    }

    String getTimeAndDateOfSale() {
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

    private void setTimeAndDateOfSale() {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();
        this.timeAndDateOfSale = dateTimeFormat.format(now);
    }
}