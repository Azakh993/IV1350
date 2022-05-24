package se.kth.iv1350.posSystem.model;

import se.kth.iv1350.posSystem.dto.*;
import se.kth.iv1350.posSystem.utilities.Amount;
import se.kth.iv1350.posSystem.utilities.TimeAndDate;

/**
 * Class responsible for handling operations associated to sale instances.
 */
public class SaleHandler {
	private final Basket basket;
	private final Payment payment;
	private CustomerDTO customerDTO;
	private boolean saleActive;

	/**
	 * Creates an instance of <code>SaleHandler</code>, and stores references to new instances of <code>Basket</code>
	 * and <code>Payment</code> Also sets <code>saleActive</code> to true
	 */
	public SaleHandler() {
		this.basket = new Basket();
		this.payment = new Payment();
		this.saleActive = true;
	}

	/**
	 * Sets items in the basket, either by adding the new item or incrementing the previously registered item's
	 * quantity
	 * @param item The <code>ItemDTO</code> to add to basket
	 */
	public void addItemToBasket(ItemDTO item) {
		basket.setItemInBasket(item);
		BasketDTO basketDTO = basket.getBasketDTO();
		payment.setTotalPriceAndVAT(basketDTO);
	}

	/**
	 * Retrieves <code>BasketDTO</code>, containing data on registered items
	 * @return The <code>BasketDTO</code> with all items currently added to the basket
	 */
	public BasketDTO fetchBasketDTO() {
		return basket.getBasketDTO();
	}

	/**
	 * Retrieves <code>totalPrice</code>, used as running total during item registration phase of the sale
	 * @return The <code>totalPrice</code>
	 */
	public Amount fetchTotalPrice() {
		return payment.getTotalPrice();
	}

	/**
	 * Gets the <code>saleActive</code>, which indicates whether a new sale is started or not
	 */
	public boolean getSaleActive() {
		return saleActive;
	}

	/**
	 * Sets the <code>saleActive</code> to false, indicating that item registration is over
	 */
	public void setSaleActiveToFalse() {
		saleActive = false;
	}

	/**
	 * Sets <code>discount</code> in <code>Payment</code>. The amount of
	 * <code>discount</code> depends on whether the <code>customerID</code> is registered,
	 * the <code>itemsInBasket</code> and <code>totalPrice</code>
	 * @param customerDTO The registered customer information
	 * @param discountDTO The currently offered discounts and their conditions
	 * @return The <code>totalPriceAfterDiscount</code>
	 */
	public Amount addDiscount(CustomerDTO customerDTO, DiscountDTO discountDTO) {
		this.customerDTO = customerDTO;
		payment.setDiscountData(basket.getBasketDTO(), discountDTO);
		return payment.getTotalPriceAfterDiscount();
	}

	/**
	 * Sets the amount paid and the change (based on the <code>totalPrice</code>), and then generates a
	 * <code>ReceiptDTO</code> object representing a receipt. If discount has been applied, the receipt has added
	 * information with the discount data
	 * @param amountPaid The amount paid by the customer in cash
	 * @return The <code>ReceiptDTO</code>
	 */
	public ReceiptDTO generateReceiptDTO(Amount amountPaid) {
		payment.setAmountPaidAndChange(amountPaid);
		BasketDTO basketDTO = basket.getBasketDTO();
		PaymentDTO paymentDTO = payment.getPaymentDTO();
		TimeAndDate timeAndDateOfSale = new TimeAndDate();
		if (customerDTO == null)
			return new ReceiptDTO(basketDTO, paymentDTO, timeAndDateOfSale);
		return new ReceiptDTO(basketDTO, paymentDTO, timeAndDateOfSale, customerDTO);
	}
}
