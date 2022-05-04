package se.kth.iv1350.posSystem.utilities;

/**
 * Represents various types of amounts involved in the program
 */
public class Amount {
    private final double amount;

    /**
     * Creates an instance of an amount
     *
     * @param amount The amount to be represented by the object
     */
    public Amount(double amount) {
        this.amount = amount;
    }

    /**
     * Gets the value of the stored amount
     *
     * @return The value of the stored amount
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * Adds the specified amount from the stored amount
     *
     * @param amountToAdd The amount to add
     * @return The reference to a new Amount instance representing the result of the addition
     */
    public Amount plus(Amount amountToAdd) {
        return new Amount(this.amount + amountToAdd.getAmount());
    }

    /**
     * Multiplies the specified amount from the stored amount
     *
     * @param amountToMultiplyWith The amount to multiply with
     * @return The reference to a new Amount instance representing the result of the multiplication
     */
    public Amount multipliedWith(Amount amountToMultiplyWith) {
        return new Amount(this.amount * amountToMultiplyWith.getAmount());
    }

    /**
     * Subtracts the specified amount from the stored amount
     *
     * @param amountToSubtract The amount to subtract
     * @return The reference to a new Amount instance representing the result of the subtraction
     */
    public Amount minus(Amount amountToSubtract) {
        return new Amount(this.amount - amountToSubtract.getAmount());
    }

    /**
     * Checks if specified amount is equal to the amount of this instance.
     *
     * @param anObject Specified amount to compare
     * @return True if the objects match; false otherwise
     */
    @Override
    public boolean equals(Object anObject) {
        if (anObject == null || !(anObject instanceof Amount))
            return false;

        Amount anotherAmount = (Amount) anObject;
        return this.amount == anotherAmount.amount;
    }

    /**
     * Generates a textual representation of the object instance
     *
     * @return String representation of Amount instance
     */
    public String toString() {
        return String.format("%.2f", this.amount);
    }
}
