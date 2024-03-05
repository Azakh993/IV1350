package se.kth.iv1350.posSystem.model;

import se.kth.iv1350.posSystem.dto.BasketDTO;
import se.kth.iv1350.posSystem.dto.ItemDTO;
import se.kth.iv1350.posSystem.utilities.Amount;

import java.util.LinkedHashMap;

class Basket {
	private final LinkedHashMap<ItemDTO, Amount> itemsInBasket = new LinkedHashMap<>();

	private ItemDTO lastRegisteredItem;
	private BasketDTO basketDTO;

	void setItemInBasket(ItemDTO item) {
		setLastRegisteredItem(item);
		if (itemInBasket(item))
			incrementItemInBasket();
		else
			setNewItemInList();
		setBasketDTO();
	}

	BasketDTO getBasketDTO() {
		return basketDTO;
	}

	void setBasketDTO() {
		this.basketDTO = new BasketDTO(getItemsInBasket(), getLastRegisteredItem());
	}

	private LinkedHashMap<ItemDTO, Amount> getItemsInBasket() {
		return itemsInBasket;
	}

	private ItemDTO getLastRegisteredItem() {
		return lastRegisteredItem;
	}

	private void setLastRegisteredItem(ItemDTO item) {
		this.lastRegisteredItem = item;
	}

	private boolean itemInBasket(ItemDTO item) {
		return itemsInBasket.containsKey(item);
	}

	private void setNewItemInList() {
		Amount defaultItemQty = new Amount(1);
		setItemQtyInBasket(defaultItemQty);
	}

	private void incrementItemInBasket() {
		Amount qtyToAdd = new Amount(1);
		Amount newItemQty = itemsInBasket.get(lastRegisteredItem).plus(qtyToAdd);
		setItemQtyInBasket(newItemQty);
	}

	private void setItemQtyInBasket(Amount itemQty) {
		itemsInBasket.put(lastRegisteredItem, itemQty);
	}
}
