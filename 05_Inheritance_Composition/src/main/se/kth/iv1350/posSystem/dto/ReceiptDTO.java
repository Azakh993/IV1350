package se.kth.iv1350.posSystem.dto;

import se.kth.iv1350.posSystem.utilities.Amount;
import se.kth.iv1350.posSystem.utilities.TimeAndDate;

import java.util.LinkedHashMap;

/**
 * Represents the receipt data of a sale
 */
public class ReceiptDTO {

	private final TimeAndDate timeAndDateOfSale;
	private final LinkedHashMap<ItemDTO, Amount> itemsInBasket;
	private final Amount totalPrice;
	private final Amount totalVAT;
	private final Amount discount;
	private final Amount totalPriceAfterDiscount;
	private final Amount totalVATAfterDiscount;
	private final String customerName;
	private final Amount amountPaid;
	private final Amount change;

	/**
	 * Creates a new instance of Receipt if discount has not been applied to the sale
	 * @param basketDTO         The basket containing items purchased
	 * @param paymentDTO        The payment information associated to transaction
	 * @param timeAndDateOfSale The time and date of the transaction finalization
	 */
	public ReceiptDTO(BasketDTO basketDTO, PaymentDTO paymentDTO, TimeAndDate timeAndDateOfSale) {
		this.timeAndDateOfSale = timeAndDateOfSale;
		this.itemsInBasket = basketDTO.getBasket();
		this.totalPrice = paymentDTO.getTotalPrice();
		this.totalVAT = paymentDTO.getTotalVAT();
		this.discount = new Amount(0);
		this.totalPriceAfterDiscount = paymentDTO.getTotalPrice();
		this.totalVATAfterDiscount = paymentDTO.getTotalVATAfterDiscount();
		this.customerName = "Anonymous Customer";
		this.amountPaid = paymentDTO.getAmountPaid();
		this.change = paymentDTO.getChange();
	}

	/**
	 * Creates a new instance of Receipt if discount has not been applied to the sale
	 * @param basketDTO         The basket containing items purchased
	 * @param paymentDTO        The payment information associated to transaction
	 * @param timeAndDateOfSale The time and date of the transaction finalization
	 * @param customerDTO       The registered customer information required for discount eligibility
	 */
	public ReceiptDTO(BasketDTO basketDTO, PaymentDTO paymentDTO,
	                  TimeAndDate timeAndDateOfSale, CustomerDTO customerDTO) {
		this.timeAndDateOfSale = timeAndDateOfSale;
		this.itemsInBasket = basketDTO.getBasket();
		this.totalPrice = paymentDTO.getTotalPrice();
		this.totalVAT = paymentDTO.getTotalVAT();
		this.discount = paymentDTO.getDiscount();
		this.totalPriceAfterDiscount = paymentDTO.getTotalPriceAfterDiscount();
		this.totalVATAfterDiscount = paymentDTO.getTotalVATAfterDiscount();
		this.customerName = customerDTO.getName();
		this.amountPaid = paymentDTO.getAmountPaid();
		this.change = paymentDTO.getChange();
	}

	/**
	 * Gets the items in the basket of a sale
	 * @return The LinkedHashMap of items and their quantity
	 */
	public LinkedHashMap<ItemDTO, Amount> getItemsInBasket() {
		return itemsInBasket;
	}

	/**
	 * Generates a textual representation of the object instance
	 * @return String representation of ReceiptDTO instance
	 */
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\t* * * * * * * * RECEIPT * * * * * * * * *\n\t*\t\t\t");
		stringBuilder.append(getTimeAndDateOfSale().getTimeAndDate());
		stringBuilder.append("\t\t\t*\n\t*\t\t\t\t\t\t\t\t\t\t*\n");
		for (ItemDTO item : itemsInBasket.keySet()) {
			stringBuilder.append(String.format("\t*\t%.0fX\t", itemsInBasket.get(item).getAmount()));
			stringBuilder.append(item.toString());
			stringBuilder.append("\t\t*\n");
		}
		stringBuilder.append("\t*\t\t\t\t\t\t\t\t\t\t*\n\t*\tTotal: ");
		stringBuilder.append(getTotalPrice());
		stringBuilder.append("\t\t\t\t\t\t*\n\t*\tTotal VAT: ");
		stringBuilder.append(getTotalVAT());
		stringBuilder.append("\t\t\t\t\t*\n\t*\t\t\t\t\t\t\t\t\t\t*");
		if (discount.getAmount() != 0) {
			stringBuilder.append("\n\t*\tDiscount: ");
			stringBuilder.append(getDiscount());
			stringBuilder.append("\t\t\t\t\t*\n\t*\tTotal after discount: ");
			stringBuilder.append(getTotalPriceAfterDiscount());
			stringBuilder.append("\t\t*\n\t*\tTotal VAT: ");
			stringBuilder.append(getTotalVATAfterDiscount());
			stringBuilder.append("\t\t\t\t\t*\n\t*\t\t\t\t\t\t\t\t\t\t*");
		}
		stringBuilder.append("\n\t*\tPaid: ");
		stringBuilder.append(getAmountPaid());
		stringBuilder.append("\t\t\t\t\t\t*\n");
		stringBuilder.append("\t*\tChange ");
		stringBuilder.append(getChange());
		stringBuilder.append("\t\t\t\t\t\t*\n");
		stringBuilder.append("\t* * * * * * * * * * * * * * * * * * * * *\n");
		return stringBuilder.toString();
	}

	/**
	 * Gets the time and date of the sale
	 * @return The time and date of the sale
	 */
	public TimeAndDate getTimeAndDateOfSale() {
		return timeAndDateOfSale;
	}

	/**
	 * Gets the running total / total price of the sale
	 * @return The running total / total price
	 */
	public Amount getTotalPrice() {
		return totalPrice;
	}

	/**
	 * Gets the total VAT of the sale
	 * @return The total VAT
	 */
	public Amount getTotalVAT() {
		return totalVAT;
	}

	/**
	 * Gets the discount applied to the sale
	 * @return The <code>discount</code>
	 */
	public Amount getDiscount() {
		return discount;
	}

	/**
	 * Gets the total price after applying discount
	 * @return The <code>totalPriceAfterDiscount</code>
	 */
	public Amount getTotalPriceAfterDiscount() {
		return totalPriceAfterDiscount;
	}

	/**
	 * Gets the total VAT after applying discount
	 * @return The <code>totalVATAfterDiscount</code>
	 */
	public Amount getTotalVATAfterDiscount() {
		return totalVATAfterDiscount;
	}

	/**
	 * Gets the amount paid by the customer
	 * @return The amount paid in cash
	 */
	public Amount getAmountPaid() {
		return amountPaid;
	}

	/**
	 * Gets the change to give to customer
	 * @return The change in cash
	 */
	public Amount getChange() {
		return change;
	}

	/**
	 * Gets the name of the registered customer
	 * @return The name of the customer
	 */
	public String getCustomerName() {
		return customerName;
	}
}

