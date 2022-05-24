package se.kth.iv1350.posSystem.dto;

import se.kth.iv1350.posSystem.utilities.Amount;

import java.util.LinkedHashMap;

/**
 * Represents the basket of items of a sale instance.
 */
public class BasketDTO {

	private final LinkedHashMap<ItemDTO, Amount> itemsInBasket;
	private final ItemDTO lastRegisteredItem;

	/**
	 * Creates a new instance of <code>BasketDTO</code>, which contains data on items added to the basket.
	 * @param itemsInBasket      The currently registered items in the <code>Basket</code>
	 * @param lastRegisteredItem The last registered <code>ItemDTO</code>
	 */
	public BasketDTO(LinkedHashMap<ItemDTO, Amount> itemsInBasket, ItemDTO lastRegisteredItem) {
		this.itemsInBasket = itemsInBasket;
		this.lastRegisteredItem = lastRegisteredItem;
	}

	/**
	 * Gets the items in the basket of a sale
	 * @return The LinkedHashMap of items and their quantity
	 */
	public LinkedHashMap<ItemDTO, Amount> getBasket() {
		return this.itemsInBasket;
	}

	/**
	 * Gets the <code>lastRegisteredItem</code>
	 * @return The <code>lastRegisteredItem</code>
	 */
	public ItemDTO getLastRegisteredItem() {
		return this.lastRegisteredItem;
	}
}

