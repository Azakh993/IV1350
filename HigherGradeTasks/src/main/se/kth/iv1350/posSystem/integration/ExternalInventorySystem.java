package se.kth.iv1350.posSystem.integration;

import se.kth.iv1350.posSystem.dto.ItemDTO;
import se.kth.iv1350.posSystem.dto.ReceiptDTO;
import se.kth.iv1350.posSystem.utilities.Amount;

import java.util.HashMap;

class ExternalInventorySystem {
	private static final ExternalInventorySystem EXTERNAL_INVENTORY_SYSTEM = new ExternalInventorySystem();
	private final HashMap<String, ItemDTO> itemCatalogue = new HashMap<>();
	private final HashMap<String, Amount> itemInventory = new HashMap<>();

	private ExternalInventorySystem() {
		setItemCatalogue();
		setStartingInventory();
	}

	private void setItemCatalogue() {
		this.itemCatalogue.put("95867956", new ItemDTO("95867956", "Black Keyboard",
				new Amount(1499), new Amount(0.25)));
		this.itemCatalogue.put("24123412", new ItemDTO("24123412", "Wireless Speaker",
				new Amount(349), new Amount(0.25)));
		this.itemCatalogue.put("12312234", new ItemDTO("12312234", "Blue Headphones",
				new Amount(799), new Amount(0.25)));
		this.itemCatalogue.put("67334553", new ItemDTO("67334553", "HDMI Cable, 2m",
				new Amount(299), new Amount(0.25)));
		this.itemCatalogue.put("32784623", new ItemDTO("32784623", "Wireless Mouse",
				new Amount(599), new Amount(0.25)));
	}

	private void setStartingInventory() {
		this.itemInventory.put("95867956", new Amount(1));
		this.itemInventory.put("24123412", new Amount(5));
		this.itemInventory.put("12312234", new Amount(32));
		this.itemInventory.put("67334553", new Amount(12));
		this.itemInventory.put("32784623", new Amount(3));
	}

	static ExternalInventorySystem getExternalInventorySystem() {
		return EXTERNAL_INVENTORY_SYSTEM;
	}

	ItemDTO getItem(String itemID) throws ItemIdentifierException, ExternalSystemException {
		if (itemID.equals("404"))
			throw new ExternalSystemException(this);
		if (!itemCatalogue.containsKey(itemID))
			throw new ItemIdentifierException(itemID);
		return this.itemCatalogue.get(itemID);
	}

	HashMap<String, ItemDTO> getItemCatalogue() {
		return this.itemCatalogue;
	}

	HashMap<String, Amount> getItemInventory() {
		return this.itemInventory;
	}

	void setItemInventory(ReceiptDTO receiptDTO) {
		HashMap<ItemDTO, Amount> soldItems = receiptDTO.getItemsInBasket();
		for (ItemDTO item : soldItems.keySet()) {
			String itemID = item.getItemID();
			Amount soldItemQty = soldItems.get(item);
			Amount currentItemQty = this.itemInventory.get(itemID);
			this.itemInventory.put(itemID, currentItemQty.minus(soldItemQty));
		}
	}
}