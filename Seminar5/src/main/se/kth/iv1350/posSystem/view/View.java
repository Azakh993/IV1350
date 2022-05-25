package se.kth.iv1350.posSystem.view;

import se.kth.iv1350.posSystem.controller.Controller;
import se.kth.iv1350.posSystem.dto.ReceiptDTO;
import se.kth.iv1350.posSystem.integration.CustomerRegistrationException;
import se.kth.iv1350.posSystem.integration.ItemIdentifierException;
import se.kth.iv1350.posSystem.utilities.Amount;
import se.kth.iv1350.posSystem.utilities.FileLogger;

/**
 * Represents the only view of the program
 */
public class View {
	private final static String LOG_FILE_PATH = "Seminar5/textFiles/log.txt";
	private final Controller controller;
	private final OutputFormatter outputFormatter;
	private final FileLogger exceptionLogger;

	/**
	 * Creates a new instance of view
	 * @param controller The reference to the only controller instance
	 */
	public View(Controller controller) {
		this.controller = controller;
		this.outputFormatter = new OutputFormatter();
		this.controller.addPaymentObserver(new TotalRevenueView());
		this.controller.addPaymentObserver(new TotalRevenueFileOutput(outputFormatter));
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
			registerPayment(2500);
		} catch (Exception exception) {
			logException(exception);
			System.out.println("Operation failed. Please try again.");
		}
	}

	private void startSale() {
		controller.startSale();
		outputFormatter.startSalePrinter();
	}

	private void addItemToBasket(String itemID) {
		try {
			outputFormatter.addIemToBasketPrinter(controller.addItem(itemID));
			outputFormatter.runningTotalPrinter(controller.fetchTotalPrice());
		} catch (ItemIdentifierException exception) {
			System.out.println("Item identifier '" + itemID + "' is invalid.");
		} catch (Exception exception) {
			logException(exception);
			System.out.println("Could not register item. Try again.");
		}
	}

	private void endSale() {
		Amount totalPrice = controller.endSale();
		outputFormatter.endSalePrinter(totalPrice);
	}

	private void addDiscounts(String customerID) {
		try {
			Amount totalPriceAfterDiscount = controller.requestDiscount(customerID);
			outputFormatter.addDiscountsPrinter(totalPriceAfterDiscount, customerID);
		} catch (CustomerRegistrationException exception) {
			System.out.println("Customer ID '" + customerID + "' is not registered.");
		}
	}

	private void registerPayment(double amountPaid) {
		ReceiptDTO receiptDTO = controller.registerPayment(amountPaid);
		outputFormatter.registerPaymentPrinter(receiptDTO);
	}

	private void logException(Exception exception) {
		String formattedLogEntry = outputFormatter.exceptionLogEntryFormatter(exception);
		exceptionLogger.addEntryToLog(formattedLogEntry);
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
			registerPayment(300);
		} catch (Exception exception) {
			logException(exception);
		}
	}
}
