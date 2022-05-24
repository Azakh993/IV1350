package se.kth.iv1350.posSystem.model.discountCalculation;

import se.kth.iv1350.posSystem.dto.DiscountDTO;
import se.kth.iv1350.posSystem.dto.ItemDTO;
import se.kth.iv1350.posSystem.utilities.Amount;

import java.util.LinkedHashMap;

/**
 * Allows for processing discounts of different types. A class containing a relevant algorithm for discount calculation
 * should implement this interface.
 */
public interface DiscountStrategy {
	Amount processDiscount(DiscountDTO discountDTO, LinkedHashMap<ItemDTO, Amount> itemsInBasket, Amount totalPrice);
}
