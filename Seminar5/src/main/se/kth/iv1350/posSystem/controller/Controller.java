package se.kth.iv1350.posSystem.controller;

import se.kth.iv1350.posSystem.dto.*;
import se.kth.iv1350.posSystem.integration.CustomerRegistrationException;
import se.kth.iv1350.posSystem.integration.ItemIdentifierException;
import se.kth.iv1350.posSystem.integration.SystemHandler;
import se.kth.iv1350.posSystem.model.PaymentObserver;
import se.kth.iv1350.posSystem.model.SaleHandler;
import se.kth.iv1350.posSystem.utilities.Amount;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the controller of the program All calls to the model and integration layer occur via this class
 */
public class Controller {
	private final SystemHandler systemHandler;
	private final List<PaymentObserver> paymentObserversList = new ArrayList<>();
	private SaleHandler saleHandler;

	/**
	 * Creates a new <code>Controller</code> object Calls for creation of a new <code>SystemHandler</code> object
	 */
	public Controller() {
		this.systemHandler = new SystemHandler();
	}

	/**
	 * Marks the start of a sale;  initializes a new instance of <code>SaleHandler</code> and adds new
	 * <code>PaymentObserver</code>s
	 */
	public void startSale() {
		this.saleHandler = new SaleHandler();
		saleHandler.addPaymentObservers(paymentObserversList);
	}

	/**
	 * Retrieves item information and stores the information in basket Updates <code>ItemsInBasket</code>,
	 * <code>totalPrice</code> and <code>totalVAT</code>
	 * @param itemID The unique identifier for an item
	 * @return The latest instance of <code>BasketDTO</code>
	 * @throws ItemIdentifierException if provided <code>itemID</code> is invalid
	 * @throws IllegalStateException   if the method is called before <code>startSale</code>
	 */
	public BasketDTO addItem(String itemID) throws ItemIdentifierException {
		if (!saleActive())
			throw new IllegalStateException("Attempt to register item before starting a new sale.");
		ItemDTO item = systemHandler.fetchItem(itemID);
		saleHandler.addItemToBasket(item);
		return saleHandler.fetchBasketDTO();
	}

	private boolean saleActive() {
		if (saleHandler == null)
			return false;
		return saleHandler.getSaleActive();
	}

	/**
	 * Marks the end of item registration by setting <code>saleActive</code> to false Also retrieves
	 * <code>totalPrice</code>
	 * @return The latest instance of <code>ReceiptDTO</code>
	 * @throws IllegalStateException if the method is called before <code>startSale</code>
	 */
	public Amount endSale() {
		if (!saleActive())
			throw new IllegalStateException("Attempt to end sale before starting a new sale.");
		saleHandler.setSaleActiveToFalse();
		return fetchTotalPrice();
	}

	/**
	 * Retrieves the <code>totalPrice</code> which works as the running total during the item registration phase of the
	 * sale.
	 * @return The <code>totalPrice</code>
	 */
	public Amount fetchTotalPrice() {
		return saleHandler.fetchTotalPrice();
	}

	/**
	 * Retrieves current discounts and their conditions if <code>customerID</code> is registered in
	 * <code>CustomerDatabase</code>, and checks if current transaction meets any of the requirements. If one or more
	 * requirements are met, a discount is applied to the transaction and the <code>totalPriceAfterDiscount</code> is
	 * returned
	 * @param customerID The unique customer identifier
	 * @return The <code>totalPriceAfterDiscount</code>
	 * @throws IllegalStateException         If the method is called before <code>endSale</code> is called
	 * @throws CustomerRegistrationException If the <code>customerID</code> is not registered
	 */
	public Amount requestDiscount(String customerID) throws CustomerRegistrationException {
		if (saleActive())
			throw new IllegalStateException("Attempt to request discount before starting ending sale.");
		CustomerDTO customerDTO = systemHandler.fetchCustomerDTO(customerID);
		DiscountDTO discountDTO = systemHandler.fetchDiscounts();
		return saleHandler.addDiscount(customerDTO, discountDTO);
	}

	/**
	 * Finalizes the transaction by generating <code>receiptDTO</code> and forwarding it to
	 * <code>SaleHandler</code> for logging and processing of sale data.
	 * @param amountPaidInCash The amount paid by customer in cash
	 * @return The <code>ReceiptDTO</code>
	 * @throws IllegalStateException if the method is called before <code>addItem</code>
	 */
	public ReceiptDTO registerPayment(double amountPaidInCash) {
		if (saleActive())
			throw new IllegalStateException("Attempt to register payment before ending sale.");
		ReceiptDTO receiptDTO = saleHandler.generateReceiptDTO(new Amount(amountPaidInCash));
		systemHandler.registerTransaction(receiptDTO);
		return receiptDTO;
	}

	/**
	 * Adds the provided observer to the observers list
	 * @param paymentObserver The observer that should be notified.
	 */
	public void addPaymentObserver(PaymentObserver paymentObserver) {
		paymentObserversList.add(paymentObserver);
	}
}
