package se.kth.iv1350.posSystem.view;

import se.kth.iv1350.posSystem.controller.Controller;
import se.kth.iv1350.posSystem.controller.OperationFailedException;
import se.kth.iv1350.posSystem.integration.ItemIdentifierException;
import se.kth.iv1350.posSystem.model.ItemDTO;
import se.kth.iv1350.posSystem.model.SaleDTO;
import se.kth.iv1350.posSystem.utilities.Amount;
import se.kth.iv1350.posSystem.utilities.FileLogger;

/**
 * Represents the only view of the program
 */
public class View {
    private final Controller controller;
    private final ExceptionFormatter exceptionFormatter;
    private final static String LOG_FILE_PATH = "Seminar4/textFiles/log.txt";
    private final FileLogger exceptionLogger;

    /**
     * Creates a new instance of view
     *
     * @param controller The reference to the only controller instance
     */
    public View(Controller controller) {
        this.controller = controller;
        this.controller.addSaleLogObserver(new TotalRevenueView());
        this.controller.addSaleLogObserver(new TotalRevenueFileOutput());
        this.exceptionFormatter = new ExceptionFormatter();
        this.exceptionLogger = new FileLogger(LOG_FILE_PATH);
    }

    /**
     * Launches a sample sale to test and illustrate program functionality
     */
    public void sampleSale() {
        try {
            startSalePrinter();
            addValidItemToBasket("95867956");
            registerInvalidItemID("1231223");
            addValidItemToBasket("12312234");
            addValidItemToBasket("67334553");
            addItemSimulatingExternalSystemConnectionFailure("404");
            addValidItemToBasket("67334553");
            endSalePrinter(this.controller.endSale());
            paymentPrinter(this.controller.registerPayment(1800));
        } catch (Exception exception) {
            handleException(exception);
        }
    }

    private void addItemSimulatingExternalSystemConnectionFailure(String itemIDTriggeringConnectionFailure) {
        try {
            System.out.println("[addItem('404'), simulates connection failure]");
            basketPrinter(this.controller.addItem(itemIDTriggeringConnectionFailure));
        } catch (OperationFailedException | ItemIdentifierException exception) {
            handleException(exception);
        }
    }

    private void addValidItemToBasket(String itemID) {
        try {
            basketPrinter(this.controller.addItem(itemID));
        } catch (ItemIdentifierException | OperationFailedException exception) {
            handleException(exception);
        }
    }

    private void registerInvalidItemID(String itemID) {
        try {
            System.out.println("[addItem('1231223'), an invalid item identifier]");
            basketPrinter(this.controller.addItem(itemID));
        } catch (ItemIdentifierException exception) {
            exceptionMessagePrinter(exception);
        } catch (OperationFailedException exception) {
            handleException(exception);
        }
    }

    private void startSalePrinter() {
        System.out.println("\n\n[startSale()]\n");
        this.controller.startSale();
    }

    private void basketPrinter(SaleDTO saleDTO) {
        ItemDTO lastRegisteredItem = saleDTO.getLastRegisteredItem();
        String itemIDOfLastRegisteredItem = lastRegisteredItem.getItemID();
        Amount qtyOfLastRegisteredItem = saleDTO.getItemsInBasket().get(lastRegisteredItem);

        System.out.println("[addItem('" + itemIDOfLastRegisteredItem + "')]");
        System.out.println(String.format("%.0f", qtyOfLastRegisteredItem.getAmount()) + "* " + lastRegisteredItem);
        System.out.printf("Running total (including VAT): " + saleDTO.getTotalPrice() + "\n\n");
    }

    private void endSalePrinter(SaleDTO saleDTO) {
        System.out.println("[endSale()]");
        System.out.printf("Total Price (including VAT): " + saleDTO.getTotalPrice() + "\n\n");
    }

    private void paymentPrinter(SaleDTO saleDTO) {
        System.out.println("[registerPayment(1800)]");
        System.out.println("[printReceipt(saleDTO)]");
        System.out.printf("Change: " + saleDTO.getChange() + " \n\n");
    }

    private void handleException(Exception exception) {
        exceptionMessagePrinter(exception);
        String formattedLogEntry = this.exceptionFormatter.exceptionLogEntryFormatter(exception);
        exceptionLogger.addEntryToLog(formattedLogEntry);
    }

    private void exceptionMessagePrinter(Exception exception) {
        System.out.println(this.exceptionFormatter.exceptionMessageFormatter(exception));
    }
}
