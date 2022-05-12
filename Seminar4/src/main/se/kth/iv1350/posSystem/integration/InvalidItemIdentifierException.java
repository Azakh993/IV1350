package se.kth.iv1350.posSystem.integration;

/**
 * Thrown when an invalid item identifier is registered during a sale
 */
public class InvalidItemIdentifierException extends Exception {
    private final String invalidItemID;

    /**
     * Creates a new instance with an error message, which specifies which item identifier is invalid
     * @param invalidItemID The invalid item identifier.
     */
    public InvalidItemIdentifierException(String invalidItemID) {
        super("Item identifier '" + invalidItemID + "' not found in item catalogue!");
        this.invalidItemID = invalidItemID;
    }

    /**
     * Gets the invalid item identifier
     * @return The invalid item identifier
     */
    public String getInvalidItemID() {
        return invalidItemID;
    }
}
