package se.kth.iv1350.posSystem.model.discountCalculation;

import se.kth.iv1350.posSystem.dto.DiscountDTO;
import se.kth.iv1350.posSystem.dto.ItemDTO;
import se.kth.iv1350.posSystem.utilities.Amount;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * A <code>DiscountStrategy</code> that calculates the eligible discount amount based on item-based discounts.
 */
public class ItemBasedDiscount implements DiscountStrategy {

	/**
	 * The algorithm for calculating the total item-based discount one might be eligible for based on the
	 * <code>itemsInBasket</code>
	 * @param discountDTO   The discounts currently offered
	 * @param itemsInBasket The items in the basket
	 * @param totalPrice    Total price for the items in the basket. Redundant for this algorithm
	 * @return The <code>discount</code>
	 */
	public Amount processDiscount(DiscountDTO discountDTO,
	                              LinkedHashMap<ItemDTO, Amount> itemsInBasket, Amount totalPrice) {
		List<DiscountDTO> discounts = discountDTO.getDiscountDTOsList();
		Amount itemBasedDiscount = new Amount(0);
		for (ItemDTO item : itemsInBasket.keySet())
			for (DiscountDTO discount : discounts)
				if (!discount.isTotalBasedDiscount() && discount.getItemIDRequirement().equals(item.getItemID()))
					if (discount.getItemQtyRequirement().equals(itemsInBasket.get(item))) {
						Amount itemPrice = item.getItemPrice();
						Amount itemDiscount = discount.getDiscount().multipliedWith(discount.getItemQtyRequirement());
						itemBasedDiscount = itemBasedDiscount.plus(itemDiscount.multipliedWith(itemPrice));
					}
		return itemBasedDiscount;
	}
}