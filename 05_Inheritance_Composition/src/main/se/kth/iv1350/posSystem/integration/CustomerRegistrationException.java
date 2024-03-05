package se.kth.iv1350.posSystem.integration;

/**
 * Thrown when a customer is not registered in the <code>CustomerDatabase</code>
 */
public class CustomerRegistrationException extends Exception {
	/**
	 * Creates a new instance with an error message, which specifies which <code>customerID</code> is unregistered
	 * @param customerID The invalid item identifier.
	 */
	public CustomerRegistrationException(String customerID) {
		super("CustomerID '" + customerID + "' is unregistered!");
	}
}
