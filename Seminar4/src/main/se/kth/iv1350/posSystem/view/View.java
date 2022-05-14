package se.kth.iv1350.posSystem.view;

import se.kth.iv1350.posSystem.controller.Controller;
import se.kth.iv1350.posSystem.controller.OperationFailedException;
import se.kth.iv1350.posSystem.integration.ItemIdentifierException;
import se.kth.iv1350.posSystem.model.SaleDTO;
import se.kth.iv1350.posSystem.utilities.LogHandler;

/**
 * Represents the only view of the program
 */
public class View {
    private final Controller controller;
    private final OutputFormatter outputFormatter;
    private final LogHandler logHandler;

    /**
     * Creates a new instance of view
     *
     * @param controller The reference to the only controller instance
     */
    public View(Controller controller) {
        this.controller = controller;
        this.outputFormatter = new OutputFormatter();
        this.logHandler = new LogHandler();
    }

    /**
     * Launches a sample sale to test and illustrate program functionality
     */
    public void sampleSale() {
        try {
            startSalePrinter();
            addSelectItemsToBasket();
            endSalePrinter(this.controller.endSale());
            paymentPrinter(this.controller.registerPayment(1800));
        } catch (Exception exception) {
            handleException(exception);
        }
    }

    private void addSelectItemsToBasket() {
        try {
            basketPrinter(this.controller.addItem("95867956"));
            try {
                System.out.println("[addItem('1231223'), an invalid item identifier]");
                basketPrinter(this.controller.addItem("1231223"));
            } catch (ItemIdentifierException exception) {
                exceptionMessagePrinter(exception);
            }

            basketPrinter(this.controller.addItem("12312234"));
            basketPrinter(this.controller.addItem("67334553"));
            try {
                System.out.println("[addItem('404'), simulates connection failure]");
                basketPrinter(this.controller.addItem("404"));
            } catch (OperationFailedException exception) {
                handleException(exception);
            }

            basketPrinter(this.controller.addItem("67334553"));
        } catch (ItemIdentifierException | OperationFailedException exception) {
            handleException(exception);
        }
    }

    private void startSalePrinter() {
        this.outputFormatter.startSaleFormatter();
        this.controller.startSale();
    }

    private void basketPrinter(SaleDTO saleDTO) {
        this.outputFormatter.newItemEntryFormatter(saleDTO);
    }

    private void endSalePrinter(SaleDTO saleDTO) {
        this.outputFormatter.endSaleFormatter(saleDTO);
    }

    private void paymentPrinter(SaleDTO saleDTO) {
        this.outputFormatter.paymentFormatter(saleDTO);
    }

    private void handleException(Exception exception) {
        exceptionMessagePrinter(exception);
        String formattedLogEntry = this.outputFormatter.exceptionLogEntryFormatter(exception);
        logHandler.addExceptionToLog(formattedLogEntry);
    }

    private void exceptionMessagePrinter(Exception exception) {
        System.out.println(this.outputFormatter.exceptionMessageFormatter(exception));
    }
}
