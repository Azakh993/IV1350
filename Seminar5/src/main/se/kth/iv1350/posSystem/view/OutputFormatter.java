package se.kth.iv1350.posSystem.view;

import se.kth.iv1350.posSystem.dto.BasketDTO;
import se.kth.iv1350.posSystem.dto.ItemDTO;
import se.kth.iv1350.posSystem.dto.ReceiptDTO;
import se.kth.iv1350.posSystem.utilities.Amount;
import se.kth.iv1350.posSystem.utilities.TimeAndDate;

import java.io.PrintWriter;
import java.io.StringWriter;

class OutputFormatter {

	void startSalePrinter() {
		System.out.print("\n\n\t-----------------------------------------------------");
		System.out.println("-----------------------------------------------------");
		System.out.println("\t[startSale()]\t\t\t\t|");
	}

	void addIemToBasketPrinter(BasketDTO basketDTO) {
		ItemDTO lastRegisteredItem = basketDTO.getLastRegisteredItem();
		String itemIDOfLastRegisteredItem = lastRegisteredItem.getItemID();
		Amount qtyOfLastRegisteredItem = basketDTO.getBasket().get(lastRegisteredItem);
		System.out.print("\t[addItem('" + itemIDOfLastRegisteredItem + "')]\t\t|\t");
		System.out.print(String.format("%.0f", qtyOfLastRegisteredItem.getAmount()) + "* " + lastRegisteredItem);
	}

	void runningTotalPrinter(Amount runningTotal) {
		System.out.printf("\t\t\t\t\t| Running total: " + runningTotal + " |\n");
	}

	void endSalePrinter(Amount totalPrice) {
		System.out.print("\t[endSale()]\t\t\t\t\t|\t");
		System.out.printf(String.format("Total Price (including VAT): %.2f\n", totalPrice.getAmount()));
	}

	void addDiscountsPrinter(Amount totalPriceAfterDiscount, String customerID) {
		System.out.print("\t[addDiscount(" + customerID + ")]\t|\t");
		System.out.printf(String.format("Total Price After Discount: %.2f\n", totalPriceAfterDiscount.getAmount()));
	}

	void registerPaymentPrinter(ReceiptDTO receiptDTO) {
		System.out.print("\t[registerPayment(" + receiptDTO.getAmountPaid().getAmount() + ")]\t|\t");
		System.out.println(String.format("Change: %.2f", receiptDTO.getChange().getAmount()));
		System.out.print("\t-----------------------------------------------------");
		System.out.println("-----------------------------------------------------");
	}

	String exceptionLogEntryFormatter(Exception exception) {
		StringBuilder logEntry = new StringBuilder();
		TimeAndDate timeAndDateOfException = new TimeAndDate();
		logEntry.append(timeAndDateOfException.getTimeAndDate());
		logEntry.append("\n");
		StringWriter stackTraceAsString = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stackTraceAsString);
		exception.printStackTrace(printWriter);
		logEntry.append(stackTraceAsString);
		logEntry.append("\n\n");
		return logEntry.toString();
	}
}