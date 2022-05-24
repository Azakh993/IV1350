package se.kth.iv1350.posSystem.model;

import se.kth.iv1350.posSystem.dto.BasketDTO;
import se.kth.iv1350.posSystem.dto.DiscountDTO;
import se.kth.iv1350.posSystem.dto.ItemDTO;
import se.kth.iv1350.posSystem.dto.PaymentDTO;
import se.kth.iv1350.posSystem.utilities.Amount;

import java.util.LinkedHashMap;

class Payment {
	private Amount totalPrice;
	private Amount totalVAT;
	private Amount discount;
	private Amount totalPriceAfterDiscount;
	private Amount totalVATAfterDiscount;
	private Amount amountPaid;
	private Amount change;
	private PaymentDTO paymentDTO;

	void setTotalPriceAndVAT(BasketDTO basketDTO) {
		LinkedHashMap<ItemDTO, Amount> itemsInBasket = basketDTO.getBasket();
		Amount resetAmount = new Amount(0);
		this.totalPrice = resetAmount;
		this.totalVAT = resetAmount;
		for (ItemDTO item : itemsInBasket.keySet()) {
			Amount itemPrice = item.getItemPrice();
			Amount itemQtyInBasket = itemsInBasket.get(item);
			this.totalPrice = this.totalPrice.plus(itemPrice.multipliedWith(itemQtyInBasket));
			Amount itemVAT = itemPrice.multipliedWith(item.getItemVATRate());
			this.totalVAT = this.totalVAT.plus(itemVAT.multipliedWith(itemQtyInBasket));
		}
	}

	PaymentDTO getPaymentDTO() {
		return paymentDTO;
	}

	void setDiscountData(BasketDTO basketDTO, DiscountDTO discountDTO) {
		Discount discount = new Discount();
		setDiscount(discount.calculateDiscount(discountDTO, basketDTO.getBasket(), totalPrice));
		setTotalPriceAfterDiscount(discount.calculateTotalPriceAfterDiscount(totalPrice, this.discount));
		setTotalVATAfterDiscount(
				discount.calculateTotalVATAfterDiscount(totalPriceAfterDiscount, totalPrice, totalVAT));
		setDiscountPaymentDTO();
	}

	void setAmountPaidAndChange(Amount amountPaid) {
		setAmountPaid(amountPaid);
		setChange();
		if (discount == null)
			setNonDiscountPaymentDTO();
		else
			setDiscountPaymentDTO();
	}
	private void setDiscount(Amount discount) {
		this.discount = discount;
	}

	Amount getTotalPriceAfterDiscount() {
		return this.totalPriceAfterDiscount;
	}

	private void setTotalPriceAfterDiscount(Amount totalPriceAfterDiscount) {
		this.totalPriceAfterDiscount = totalPriceAfterDiscount;
	}

	Amount getTotalPrice() {
		return totalPrice;
	}
	private void setDiscountPaymentDTO() {
		this.paymentDTO = new PaymentDTO(totalPrice, totalVAT, amountPaid, change,
				discount, totalPriceAfterDiscount, totalVATAfterDiscount);
	}
	private void setTotalVATAfterDiscount(Amount totalVATAfterDiscount) {
		this.totalVATAfterDiscount = totalVATAfterDiscount;
	}
	private void setNonDiscountPaymentDTO() {
		this.paymentDTO = new PaymentDTO(totalPrice, totalVAT, amountPaid, change);
	}

	private void setAmountPaid(Amount amountPaid) {
		this.amountPaid = amountPaid;
	}

	private void setChange() {
		if(discount == null)
			this.change = this.amountPaid.minus(this.totalPrice);
		else
			this.change = this.amountPaid.minus(this.totalPriceAfterDiscount);
	}
}