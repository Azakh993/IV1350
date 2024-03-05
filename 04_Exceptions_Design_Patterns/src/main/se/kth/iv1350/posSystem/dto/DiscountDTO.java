package se.kth.iv1350.posSystem.dto;

import se.kth.iv1350.posSystem.utilities.Amount;

import java.util.List;

/**
 * Represents available discounts which may be offered if conditions are met
 */
public class DiscountDTO {
    private final List<DiscountDTO> discountDTOsList;
    private final boolean totalBasedDiscount;
    private final Amount totalPriceRequirement;
    private final String itemIDRequirement;
    private final Amount itemQtyRequirement;
    private final Amount discount;

    /**
     * Creates an instance of a discount which is total-based.
     *
     * @param totalPriceRequirement The minimum required <code>totalPrice</code> to be eligible for the discount
     * @param discount              The discount applied if the requirement is met
     */
    public DiscountDTO(Amount totalPriceRequirement, Amount discount) {
        this.totalBasedDiscount = true;
        this.totalPriceRequirement = totalPriceRequirement;
        this.discount = discount;
        this.itemIDRequirement = null;
        this.itemQtyRequirement = null;
        this.discountDTOsList = null;
    }

    /**
     * Creates an instance of a discount which is item-based.
     *
     * @param itemIDRequirement  The required <code>itemID</code> to be eligible for the discount
     * @param itemQtyRequirement The required quantity of <code>itemID</code> to be eligible for the discount
     * @param discount           The discount applied if the requirement is met
     */
    public DiscountDTO(String itemIDRequirement, Amount itemQtyRequirement, Amount discount) {
        this.totalBasedDiscount = false;
        this.itemIDRequirement = itemIDRequirement;
        this.itemQtyRequirement = itemQtyRequirement;
        this.discount = discount;
        this.totalPriceRequirement = null;
        this.discountDTOsList = null;
    }

    /**
     * Creates an instance of the class which holds a list of individual discounts
     *
     * @param discountDTOsList The list of discounts offered
     */
    public DiscountDTO(List<DiscountDTO> discountDTOsList) {
        this.discountDTOsList = discountDTOsList;
        this.totalPriceRequirement = null;
        this.totalBasedDiscount = false;
        this.itemIDRequirement = null;
        this.itemQtyRequirement = null;
        this.discount = null;
    }

    /**
     * Gets the <code>discountDTOsList</code> consisting of all offered discounts
     *
     * @return The <code>discountDTOsList</code>
     */
    public List<DiscountDTO> getDiscountDTOsList() {
        return this.discountDTOsList;
    }

    /**
     * Gets whether a discount is <code>totalBasedDiscount</code> or not
     *
     * @return The <code>totalBasedDiscount</code>
     */
    public boolean isTotalBasedDiscount() {
        return this.totalBasedDiscount;
    }

    /**
     * Gets the <code>totalPriceRequirement</code> of a total-based discount
     *
     * @return The <code>totalPriceRequirement</code>
     */
    public Amount getTotalPriceRequirement() {
        return totalPriceRequirement;
    }

    /**
     * Gets the <code>itemIDRequirement</code> of an item-based discount
     *
     * @return The <code>itemIDRequirement</code>
     */
    public String getItemIDRequirement() {
        return itemIDRequirement;
    }

    /**
     * Gets the <code>itemQtyRequirement</code> of an item-based discount
     *
     * @return The <code>itemQtyRequirement</code>
     */
    public Amount getItemQtyRequirement() {
        return itemQtyRequirement;
    }

    /**
     * Gets the <code>discount</code> offered by a discount
     *
     * @return The <code>discount</code>
     */
    public Amount getDiscount() {
        return discount;
    }
}