package se.kth.iv1350.posSystem.view;

import se.kth.iv1350.posSystem.controller.Controller;
import se.kth.iv1350.posSystem.model.ItemDTO;
import se.kth.iv1350.posSystem.model.SaleDTO;
import se.kth.iv1350.posSystem.utilities.Amount;

/**
 * Represents the only view of the program
 */
public class View {
    private final Controller controller;

    /**
     * Creates a new instance of view
     *
     * @param controller The reference to the only controller instance
     */
    public View(Controller controller) {
        this.controller = controller;
    }

    /**
     * Launches a sample sale to test and illustrate program functionality
     */
    public void standardSale() {
        SaleDTO newSale = this.controller.startSale();
        startSalePrinter(newSale);

        SaleDTO basket = this.controller.addItem("24123412");
        basketPrinter(basket);

        basket = this.controller.addItem("12312234");
        basketPrinter(basket);

        basket = this.controller.addItem("12312234");
        basketPrinter(basket);

        basket = this.controller.addItem("95867956");
        basketPrinter(basket);

        basket = this.controller.addItem("67334553");
        basketPrinter(basket);

        basket = this.controller.addItem("12312234");
        basketPrinter(basket);

        basket = this.controller.addItem("67334553");
        basketPrinter(basket);

        basket = this.controller.addItem("32784623");
        basketPrinter(basket);

        SaleDTO endSale = this.controller.endSale();
        endSalePrinter(endSale);

        System.out.println("[registerPayment(): Amount paid by customer in cash registered]");
        SaleDTO payment = this.controller.registerPayment(2600);
        paymentPrinter(payment);
    }

    private void startSalePrinter(SaleDTO saleDTO) {
        System.out.println("\n\n[startSale(): New sale started]\n");
    }

    private void basketPrinter(SaleDTO saleDTO) {
        ItemDTO lastRegisteredItem = saleDTO.getLastRegisteredItem();
        String itemIDOfLastRegisteredItem = lastRegisteredItem.getItemID();
        Amount qtyOfLastRegisteredItem = saleDTO.getItemsInBasket().get(lastRegisteredItem);

        System.out.println("[addItem(): itemID '" + itemIDOfLastRegisteredItem + "' entered]\n");
        System.out.println(String.format("%.0f", qtyOfLastRegisteredItem.getAmount()) + "* " + lastRegisteredItem);
        System.out.printf("Running total (including VAT): " + getTotalPrice(saleDTO) + "\n\n");
    }

    private void endSalePrinter(SaleDTO saleDTO) {
        System.out.println("[endSale(): Item registration complete]");
        System.out.printf("Total Price (including VAT): " + getTotalPrice(saleDTO) + "\n\n");
    }

    private void paymentPrinter(SaleDTO saleDTO) {
        System.out.printf("Change: " + saleDTO.getChange() + " \n\n");
    }

    private Amount getTotalPrice(SaleDTO saleDTO) {
        return saleDTO.getTotalPrice();
    }
}
