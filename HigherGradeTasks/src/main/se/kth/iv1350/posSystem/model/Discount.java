package se.kth.iv1350.posSystem.model;

import se.kth.iv1350.posSystem.dto.DiscountDTO;
import se.kth.iv1350.posSystem.dto.ItemDTO;
import se.kth.iv1350.posSystem.model.discountCalculation.DiscountStrategy;
import se.kth.iv1350.posSystem.model.discountCalculation.ItemBasedDiscount;
import se.kth.iv1350.posSystem.model.discountCalculation.TotalBasedDiscount;
import se.kth.iv1350.posSystem.utilities.Amount;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

class Discount {
	private final List<DiscountStrategy> discountStrategies = new ArrayList<>();

	Discount() {
		this.discountStrategies.add(new ItemBasedDiscount());
		this.discountStrategies.add(new TotalBasedDiscount());
	}

	Amount calculateDiscount(DiscountDTO discountDTO, LinkedHashMap<ItemDTO, Amount> itemsInBasket,
	                         Amount totalPrice) {
		Amount discount = new Amount(0);
		for (DiscountStrategy strategy : discountStrategies)
			discount = discount.plus(strategy.processDiscount(discountDTO, itemsInBasket, totalPrice));
		return discount;
	}

	Amount calculateTotalPriceAfterDiscount(Amount totalPrice, Amount discount) {
		return totalPrice.minus(discount);
	}

	Amount calculateTotalVATAfterDiscount(Amount totalPriceAfterDiscount, Amount totalPrice, Amount totalVAT) {
		Amount ratio = totalPriceAfterDiscount.dividedWith(totalPrice);
		return ratio.multipliedWith(totalVAT);
	}
}
