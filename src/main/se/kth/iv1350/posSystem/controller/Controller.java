package se.kth.iv1350.posSystem.controller;

import se.kth.iv1350.posSystem.utilities.Amount;
import se.kth.iv1350.posSystem.integration.SystemHandler;
import se.kth.iv1350.posSystem.model.Basket;
import se.kth.iv1350.posSystem.model.Payment;
import se.kth.iv1350.posSystem.model.SaleDTO;

/**
 * Represents the controller of the program
 * All calls to the model and integration layer occur via this class
 */
public class Controller {
    private Basket basket;
    private Payment payment;
    private final SystemHandler systemHandler;
    private SaleDTO saleDTO;

    /**
     * Creates a new Controller object
     * Calls for creation of a new SystemHandler object
     */
    public Controller() {
        this.systemHandler = new SystemHandler();
    }

    /**
     * Starts a new sale
     * Calls for creation of payment and Basket
     *
     * @return The latest instance of SaleDTO
     */
    public SaleDTO startSale() {
        this.payment = new Payment();
        this.basket = new Basket();
        return updateSaleDTO();
    }

    /**
     * Retrieves item information and stores the information in basket
     * Updates items in basket, running total and total VAT
     *
     * @param itemID The unique identifier for an item
     * @return The latest instance of SaleDTO
     */
    public SaleDTO addItem(String itemID) {
        this.basket.setItemInBasket(this.systemHandler.fetchItem(itemID));
        return updateSaleDTO();
    }

    /**
     * Marks the end of item registration
     *
     * @return The latest instance of SaleDTO
     */
    public SaleDTO endSale() {
        return this.saleDTO;
    }

    /**
     * Registers the payment by updating payment information with amount paid and generating receipt
     * Updates inventory and transaction logs
     *
     * @param amountPaidInCash The amount paid by customer in cash
     * @return The latest instance of SaleDTO
     */
    public SaleDTO registerPayment(double amountPaidInCash) {
        Amount amountPaid = new Amount(amountPaidInCash);
        updateAmountPaidAndChange(amountPaid, this.saleDTO.getTotalPrice());
        updateCashInRegister(amountPaid);
        updateSaleDTO();
        generateReceipt();
        updateLogs();
        return this.saleDTO;
    }

    private SaleDTO updateSaleDTO() {
        return this.saleDTO = new SaleDTO(this.payment, this.basket);
    }

    private void updateAmountPaidAndChange(Amount amountPaid, Amount amountToPay) {
        this.payment.setAmountPaidAndChange(amountPaid, amountToPay);
    }

    private void updateCashInRegister(Amount amountPaid) {
        this.systemHandler.addCashToRegister(this.saleDTO);
    }

    private void generateReceipt() {
        this.systemHandler.printReceipt(this.saleDTO);
    }

    private void updateLogs() {
        this.systemHandler.updateLogs(this.saleDTO);
    }
}
