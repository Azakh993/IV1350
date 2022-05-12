package se.kth.iv1350.posSystem.integration;

import se.kth.iv1350.posSystem.model.ItemDTO;
import se.kth.iv1350.posSystem.model.SaleDTO;
import se.kth.iv1350.posSystem.utilities.Amount;

import java.util.HashMap;

class ExternalInventorySystem {
    private final HashMap<String, ItemDTO> itemCatalogue = new HashMap<>();
    private final HashMap<String, Amount> itemInventory = new HashMap<>();

    ExternalInventorySystem() {
        setItemCatalogue();
        setStartingInventory();
    }

    ItemDTO getItem(String itemID) {
        return this.itemCatalogue.get(itemID);
    }

    HashMap<String, ItemDTO> getItemCatalogue() {
        return this.itemCatalogue;
    }

    HashMap<String, Amount> getItemInventory() {
        return this.itemInventory;
    }

    void setItemInventory(SaleDTO saleDTO) {
        HashMap<ItemDTO, Amount> soldItems = saleDTO.getItemsInBasket();

        for (ItemDTO item : soldItems.keySet()) {
            String itemID = item.getItemID();
            Amount soldItemQty = soldItems.get(item);
            Amount currentItemQty = this.itemInventory.get(itemID);
            this.itemInventory.put(itemID, currentItemQty.minus(soldItemQty));
        }
    }

    private void setItemCatalogue() {
        this.itemCatalogue.put("95867956", new ItemDTO("95867956", "Apple Keyboard",
                new Amount(1499), new Amount(0.25)));
        this.itemCatalogue.put("24123412", new ItemDTO("24123412", "Game Guide Book",
                new Amount(299), new Amount(0.06)));
        this.itemCatalogue.put("12312234", new ItemDTO("12312234", "HDMI Cable, 2m",
                new Amount(199), new Amount(0.25)));
        this.itemCatalogue.put("67334553", new ItemDTO("67334553", "Choco Ice Cream",
                new Amount(23), new Amount(0.12)));
        this.itemCatalogue.put("32784623", new ItemDTO("32784623", "USB Wired Mouse",
                new Amount(99), new Amount(0.25)));
    }

    private void setStartingInventory() {
        this.itemInventory.put("95867956", new Amount(1));
        this.itemInventory.put("24123412", new Amount(5));
        this.itemInventory.put("12312234", new Amount(32));
        this.itemInventory.put("67334553", new Amount(12));
        this.itemInventory.put("32784623", new Amount(3));
    }
}