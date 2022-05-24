package se.kth.iv1350.posSystem.dto;

/**
 * Data-container for customer-data: <code>customerID</code>, <code>name</code> and <code>mobileNo</code>
 */
public class CustomerDTO {
	private final String customerID;
	private final String name;
	private final String mobileNo;

	/**
	 * Creates an instance of <code>CustomerDTO</code> with registered customer data
	 * @param customerID The unique customer identifier
	 * @param name       The name of the registered customer
	 * @param mobileNo   The mobile number of the customer
	 */
	public CustomerDTO(String customerID, String name, String mobileNo) {
		this.customerID = customerID;
		this.name = name;
		this.mobileNo = mobileNo;
	}

	/**
	 * Gets the <code>customerID</code> of the registered customer
	 * @return The <code>customerID</code>
	 */
	public String getCustomerID() {
		return this.customerID;
	}

	/**
	 * Gets the <code>name</code> of the registered customer
	 * @return The <code>name</code>
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the <code>mobileNo</code> of the registered customer
	 * @return The <code>mobileNo</code>
	 */
	public String getMobileNo() {
		return this.mobileNo;
	}
}
