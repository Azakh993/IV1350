package se.kth.iv1350.posSystem.integration;

import se.kth.iv1350.posSystem.dto.ReceiptDTO;
import se.kth.iv1350.posSystem.utilities.Task2.PrintWriterAdaptationViaComposition;
import se.kth.iv1350.posSystem.utilities.Task2.PrintWriterAdaptationViaInheritance;

import java.io.IOException;

class ReceiptPrinter {
	private static final ReceiptPrinter RECEIPT_PRINTER = new ReceiptPrinter();

	static ReceiptPrinter getReceiptPrinter() {
		return RECEIPT_PRINTER;
	}

	void printReceipt(ReceiptDTO receiptDTO) {
		try {
			String description = "Entries are generated using an adaptation of PrintWriter;\n" +
			"the method 'println() has been modified to start with a newline.\n\n";
			PrintWriterAdaptationViaComposition compositionAdaptation = new PrintWriterAdaptationViaComposition();
			compositionAdaptation.println(description + receiptDTO);
			PrintWriterAdaptationViaInheritance inheritanceAdaptation = new PrintWriterAdaptationViaInheritance();
			inheritanceAdaptation.println(description + receiptDTO);
			inheritanceAdaptation.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
