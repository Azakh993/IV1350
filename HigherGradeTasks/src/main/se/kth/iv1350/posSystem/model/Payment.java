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
		return this.paymentDTO;
	}

	void setDiscountData(BasketDTO basketDTO, DiscountDTO discountDTO) {
		Discount discount = new Discount();
		setDiscount(discount.calculateDiscount(discountDTO, basketDTO.getBasket(), getTotalPrice()));
		setTotalPriceAfterDiscount(discount.calculateTotalPriceAfterDiscount(getTotalPrice(), getDiscount()));
		setTotalVATAfterDiscount(
				discount.calculateTotalVATAfterDiscount(getTotalPriceAfterDiscount(), getTotalPrice(), getTotalVAT()));
		setDiscountPaymentDTO();
	}

	void setAmountPaidAndChange(Amount amountPaid) {
		this.setAmountPaid(amountPaid);
		this.setChange();
		if (this.discount == null)
			setNonDiscountPaymentDTO();
		else
			setDiscountPaymentDTO();
	}

	Amount getDiscount() {
		return this.discount;
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
		return this.totalPrice;
	}

	Amount getTotalVAT() {
		return totalVAT;
	}

	private void setDiscountPaymentDTO() {
		this.paymentDTO = new PaymentDTO(getTotalPrice(), getTotalVAT(), getAmountPaid(), getChange(),
				getDiscount(), getTotalPriceAfterDiscount(), getTotalVATAfterDiscount());
	}

	Amount getTotalVATAfterDiscount() {
		return this.totalVATAfterDiscount;
	}

	private void setTotalVATAfterDiscount(Amount totalVATAfterDiscount) {
		this.totalVATAfterDiscount = totalVATAfterDiscount;
	}

	private Amount getAmountPaid() {
		return this.amountPaid;
	}

	private Amount getChange() {
		return this.change;
	}

	private void setNonDiscountPaymentDTO() {
		this.paymentDTO = new PaymentDTO(getTotalPrice(), getTotalVAT(), getAmountPaid(), getChange());
	}

	private void setAmountPaid(Amount amountPaid) {
		this.amountPaid = amountPaid;
	}

	private void setChange() {
		this.change = this.amountPaid.minus(this.totalPriceAfterDiscount);
	}
}