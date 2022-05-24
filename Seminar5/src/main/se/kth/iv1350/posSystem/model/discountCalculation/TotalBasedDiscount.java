package se.kth.iv1350.posSystem.model.discountCalculation;

import se.kth.iv1350.posSystem.dto.DiscountDTO;
import se.kth.iv1350.posSystem.dto.ItemDTO;
import se.kth.iv1350.posSystem.utilities.Amount;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * A <code>DiscountStrategy</code> that calculates the eligible discount amount based on total-based discounts.
 */
public class TotalBasedDiscount implements DiscountStrategy {

	/**
	 * Algorithm for calculating the highest total-based discount one might be eligible for based on the
	 * <code>totalPrice</code>
	 * @param discountDTO   The discounts currently offered
	 * @param itemsInBasket The items in the basket; redundant in this algorithm
	 * @param totalPrice    The total price for the items in the basket
	 * @return The <code>discount</code>
	 */
	public Amount processDiscount(DiscountDTO discountDTO, LinkedHashMap<ItemDTO, Amount> itemsInBasket,
	                              Amount totalPrice) {
		List<DiscountDTO> discounts = discountDTO.getDiscountDTOsList();
		Amount totalBasedDiscount = new Amount(0);
		for (DiscountDTO discount : discounts) {
			if (discount.isTotalBasedDiscount())
				if (totalPrice.minus(discount.getTotalPriceRequirement()).getAmount() >= 0)
					if (discount.getDiscount().minus(totalBasedDiscount).getAmount() >= 0)
						totalBasedDiscount = discount.getDiscount();
		}
		return totalBasedDiscount.multipliedWith(totalPrice);
	}
}
