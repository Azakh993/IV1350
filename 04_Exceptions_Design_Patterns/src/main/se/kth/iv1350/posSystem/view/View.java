package se.kth.iv1350.posSystem.view;

import se.kth.iv1350.posSystem.controller.Controller;
import se.kth.iv1350.posSystem.controller.OperationFailedException;
import se.kth.iv1350.posSystem.dto.BasketDTO;
import se.kth.iv1350.posSystem.dto.ItemDTO;
import se.kth.iv1350.posSystem.dto.ReceiptDTO;
import se.kth.iv1350.posSystem.integration.CustomerRegistrationException;
import se.kth.iv1350.posSystem.integration.ItemIdentifierException;
import se.kth.iv1350.posSystem.utilities.Amount;
import se.kth.iv1350.posSystem.utilities.FileLogger;

/**
 * Represents the only view of the program
 */
public class View {
    private final static String LOG_FILE_PATH = "Seminar4/textFiles/log.txt";
    private final Controller controller;
    private final ExceptionFormatter exceptionFormatter;
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
     * Launches the first sample sale to test and illustrate program functionality
     */
    public void firstSampleSale() {
        try {
            startSale();
            addItemToBasket("95867956");
            System.out.print("[addItem('1231223" +
                    "')]\t\t|\t");
            addItemToBasket("1231223");
            addItemToBasket("12312234");
            addItemToBasket("67334553");
            System.out.print("[addItem('404')]\t\t\t|\t");
            addItemToBasket("404");
            addItemToBasket("67334553");
            endSale();
            System.out.print("[addDiscount(9304050001)]\t|\t");
            addDiscounts("9304050001");
            addDiscounts("9304050000");
            registerPayment();
        } catch (Exception exception) {
            logException(exception);
        }
    }

    private void startSale() {
        this.controller.startSale();
        startSalePrinter();
    }

    private void addItemToBasket(String itemID) {
        try {
            addIemToBasketPrinter(this.controller.addItem(itemID));
            runningTotalPrinter(this.controller.fetchTotalPrice());
        } catch (ItemIdentifierException exception) {
            exceptionMessagePrinter(exception);
        } catch (OperationFailedException exception) {
            logException(exception);
            exceptionMessagePrinter(exception);
        } catch (Exception exception) {
            logException(exception);
            unknownExceptionMessagePrinter();
        }
    }

    private void endSale() {
        Amount totalPrice = this.controller.endSale();
        endSalePrinter(totalPrice);
    }

    private void addDiscounts(String customerID) {
        try {
            Amount totalPriceAfterDiscount = this.controller.requestDiscount(customerID);
            addDiscountsPrinter(totalPriceAfterDiscount, customerID);
        } catch (CustomerRegistrationException exception) {
            exceptionMessagePrinter(exception);
        } catch (Exception exception) {
            logException(exception);
            unknownExceptionMessagePrinter();
        }
    }

    private void registerPayment() {
        ReceiptDTO receiptDTO = this.controller.registerPayment(2700);
        registerPaymentPrinter(receiptDTO);
    }

    private void logException(Exception exception) {
        String formattedLogEntry = this.exceptionFormatter.exceptionLogEntryFormatter(exception);
        exceptionLogger.addEntryToLog(formattedLogEntry);
    }

    private void startSalePrinter() {
        System.out.println("\n\n[startSale()]\t\t\t\t|");
    }

    private void addIemToBasketPrinter(BasketDTO basketDTO) {
        ItemDTO lastRegisteredItem = basketDTO.getLastRegisteredItem();
        String itemIDOfLastRegisteredItem = lastRegisteredItem.getItemID();
        Amount qtyOfLastRegisteredItem = basketDTO.getBasket().get(lastRegisteredItem);

        System.out.print("[addItem('" + itemIDOfLastRegisteredItem + "')]\t\t|\t");
        System.out.print(String.format("%.0f", qtyOfLastRegisteredItem.getAmount()) + "* " + lastRegisteredItem);
    }

    private void runningTotalPrinter(Amount runningTotal) {
        System.out.printf("\t\t\t\t\t\t\t\t| Running total: " + runningTotal + " |\n");
    }

    private void exceptionMessagePrinter(Exception exception) {
        System.out.println(this.exceptionFormatter.exceptionMessageFormatter(exception));
    }

    private void unknownExceptionMessagePrinter() {
        System.out.println(this.exceptionFormatter.unknownExceptionMessageFormatter());
    }

    private void endSalePrinter(Amount totalPrice) {
        System.out.print("[endSale()]\t\t\t\t\t|\t");
        System.out.printf(String.format("Total Price (including VAT): %.2f\n", totalPrice.getAmount()));
    }

    private void addDiscountsPrinter(Amount totalPriceAfterDiscount, String customerID) {
        System.out.print("[addDiscount(" + customerID + ")]\t|\t");
        System.out.printf(String.format("Total Price After Discount: %.2f\n", totalPriceAfterDiscount.getAmount()));
    }

    private void registerPaymentPrinter(ReceiptDTO receiptDTO) {
        System.out.print("[registerPayment(" + receiptDTO.getAmountPaid().getAmount() + ")]\t|\t");
        System.out.println(String.format("Change: %.2f", receiptDTO.getChange().getAmount()));
    }

    /**
     * Launches the second sample sale to test and illustrate program functionality
     */
    public void secondSampleSale() {
        try {
            startSale();
            addItemToBasket("67334553");
            endSale();
            addDiscounts("9304050000");
            registerPayment();
        } catch (Exception exception) {
            logException(exception);
        }
    }
}
