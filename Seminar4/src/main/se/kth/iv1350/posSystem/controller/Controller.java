package se.kth.iv1350.posSystem.controller;

import se.kth.iv1350.posSystem.integration.ExternalSystemException;
import se.kth.iv1350.posSystem.integration.ItemIdentifierException;
import se.kth.iv1350.posSystem.integration.SaleLogObserver;
import se.kth.iv1350.posSystem.integration.SystemHandler;
import se.kth.iv1350.posSystem.model.Basket;
import se.kth.iv1350.posSystem.model.Payment;
import se.kth.iv1350.posSystem.model.SaleDTO;
import se.kth.iv1350.posSystem.utilities.Amount;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the controller of the program
 * All calls to the model and integration layer occur via this class
 */
public class Controller {
    private final SystemHandler systemHandler;
    private Basket basket;
    private Payment payment;
    private SaleDTO saleDTO;
    private final List<SaleLogObserver> saleLogObserversList = new ArrayList<>();


    /**
     * Creates a new <code>Controller</code> object
     * Calls for creation of a new <code>SystemHandler</code> object
     */
    public Controller() {
        this.systemHandler = new SystemHandler();
    }

    /**
     * Starts a new sale
     * Calls for creation of <code>Payment</code> and <code>Basket</code>
     */
    public void startSale() {
        this.systemHandler.addNewSaleLogObservers(saleLogObserversList);
        this.payment = new Payment();
        this.basket = new Basket();
        updateSaleDTO();
    }

    /**
     * Retrieves item information and stores the information in basket
     * Updates <code>ItemsInBasket</code>, <code>totalPrice</code> and <code>totalVAT</code>
     *
     * @param itemID The unique identifier for an item
     * @return The latest instance of <code>SaleDTO</code>
     * @throws OperationFailedException if the <code>ExternalInventorySystem</code> cannot be contacted
     * @throws ItemIdentifierException  if provided <code>itemID</code> is invalid
     * @throws IllegalStateException    if the method is called before <code>startSale</code>
     */
    public SaleDTO addItem(String itemID) throws OperationFailedException, ItemIdentifierException {
        if (this.saleDTO == null)
            throw new IllegalStateException("Attempt to register item before starting a new sale.");
        try {
            this.basket.setItemInBasket(this.systemHandler.fetchItem(itemID));
        } catch (ExternalSystemException exception) {
            throw new OperationFailedException("Could not register item. Please try again.", exception);
        }
        return updateSaleDTO();
    }

    /**
     * Marks the end of item registration
     *
     * @return The latest instance of <code>SaleDTO</code>
     * @throws IllegalStateException if the method is called before <code>startSale</code>
     */
    public SaleDTO endSale() {
        if (this.saleDTO == null)
            throw new IllegalStateException("Attempt to end sale before starting a new sale.");

        return this.saleDTO;
    }

    /**
     * Registers the payment by updating payment information with amount paid and generating receipt
     * Updates inventory and transaction logs
     *
     * @param amountPaidInCash The amount paid by customer in cash
     * @return The latest instance of <code>SaleDTO</code>
     * @throws IllegalStateException if the method is called before <code>addItem</code>
     */
    public SaleDTO registerPayment(double amountPaidInCash) {
        if (this.saleDTO == null)
            throw new IllegalStateException("Attempt to register payment before starting new sale.");

        Amount amountPaid = new Amount(amountPaidInCash);
        updateAmountPaidAndChange(amountPaid, this.saleDTO.getTotalPrice());
        updateCashInRegister();
        updateSaleDTO();
        generateReceipt();
        updateLogs();
        return this.saleDTO;
    }

    /**
     * The observer to notify when transaction has been finalized.
     *
     * @param saleLogObserver The observer that should be notified.
     */
    public void addSaleLogObserver(SaleLogObserver saleLogObserver) {
        saleLogObserversList.add(saleLogObserver);
    }

    private SaleDTO updateSaleDTO() {
        return this.saleDTO = new SaleDTO(this.payment, this.basket);
    }

    private void updateAmountPaidAndChange(Amount amountPaid, Amount amountToPay) {
        this.payment.setAmountPaidAndChange(amountPaid, amountToPay);
    }

    private void updateCashInRegister() {
        this.systemHandler.addCashToRegister(this.saleDTO);
    }

    private void generateReceipt() {
        this.systemHandler.printReceipt(this.saleDTO);
    }

    private void updateLogs() {
        this.systemHandler.updateLogs(this.saleDTO);
    }
}
