package se.kth.iv1350.posSystem.integration;

/**
 * Thrown when connection to an external system cannot be established
 */
public class ExternalSystemException extends Exception {
    private final Object externalSystemReference;

    /**
     * Creates a new instance with an error message, which specifies which external system could not be connected to
     *
     * @param externalSystemReference the external system instance reference
     */
    public ExternalSystemException(Object externalSystemReference) {
        super("Could not establish contact with '" + externalSystemReference.getClass().getSimpleName() + "'!");
        this.externalSystemReference = externalSystemReference;
    }

    /**
     * Gets the external system instance reference associated to connection failure
     *
     * @return the external system instance reference
     */
    public Object getExternalSystemReference() {
        return this.externalSystemReference;
    }
}
