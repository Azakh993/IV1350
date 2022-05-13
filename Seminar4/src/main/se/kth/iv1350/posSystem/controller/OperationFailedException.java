package se.kth.iv1350.posSystem.controller;

/**
 * Thrown when a non-user friendly exception is thrown, and user needs to be notified that the user operation failed.
 */
public class OperationFailedException extends Exception {

    /**
     * Creates a new instance with an error message and triggering exception.
     * @param errorMessage The error message.
     * @param cause The causative exception.
     */
    public OperationFailedException(String errorMessage, Exception cause) {
        super(errorMessage, cause);
    }
}
