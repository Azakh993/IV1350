package se.kth.iv1350.posSystem.model;

import se.kth.iv1350.posSystem.utilities.Amount;

import java.util.LinkedHashMap;

/**
 * Represents the basket of the sale
 * Manages items and their quantity
 */
public class Basket {
    private final LinkedHashMap<ItemDTO, Amount> itemsInBasket = new LinkedHashMap<>();
    private Amount totalPrice;
    private Amount totalVAT;
    private ItemDTO lastRegisteredItem;

    /**
     * Sets items in the basket;
     * either by adding the new item or incrementing the previously registered item's its quantity
     *
     * @param item The item instance to process
     */
    public void setItemInBasket(ItemDTO item) {
        setLastRegisteredItem(item);

        if (itemInBasket(item))
            incrementItemInBasket();

        else
            setNewItemInList();

        setTotalPriceAndTotalVAT();
    }

    /**
     * Gets the items in the basket
     *
     * @return The LinkedHashMap containing the items in basket
     */
    public LinkedHashMap<ItemDTO, Amount> getItemsInBasket() {
        return this.itemsInBasket;
    }

    ItemDTO getLastRegisteredItem() {
        return this.lastRegisteredItem;
    }

    private void setLastRegisteredItem(ItemDTO item) {
        this.lastRegisteredItem = item;
    }

    Amount getTotalPrice() {
        return this.totalPrice;
    }

    Amount getTotalVAT() {
        return this.totalVAT;
    }

    private void setTotalPriceAndTotalVAT() {
        Amount resetAmount = new Amount(0);
        this.totalPrice = resetAmount;
        this.totalVAT = resetAmount;
        for (ItemDTO item : this.itemsInBasket.keySet()) {
            this.totalPrice = this.totalPrice.plus(item.getItemPrice().multipliedWith(this.itemsInBasket.get(item)));
            Amount itemVAT = item.getItemPrice().multipliedWith(item.getItemVATRate());
            this.totalVAT = this.totalVAT.plus(itemVAT.multipliedWith(this.itemsInBasket.get(item)));
        }
    }

    private boolean itemInBasket(ItemDTO item) {
        return this.itemsInBasket.containsKey(item);
    }

    private void setItemQtyInBasket(Amount itemQty) {
        this.itemsInBasket.put(this.lastRegisteredItem, itemQty);
    }

    private void setNewItemInList() {
        Amount defaultItemQty = new Amount(1);
        setItemQtyInBasket(defaultItemQty);
    }

    private void incrementItemInBasket() {
        Amount qtyToAdd = new Amount(1);
        Amount newItemQty = this.itemsInBasket.get(this.lastRegisteredItem).plus(qtyToAdd);
        setItemQtyInBasket(newItemQty);
    }
}
