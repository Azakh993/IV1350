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
			PrintWriterAdaptationViaComposition compositionAdaptation = new PrintWriterAdaptationViaComposition();
			compositionAdaptation.println("Entries are generated using an adaptation of PrintWriter;\n" +
					"the method 'println() has been modified to start with a newline.");
			compositionAdaptation.println(receiptDTO.toString());
			PrintWriterAdaptationViaInheritance inheritanceAdaptation = new PrintWriterAdaptationViaInheritance();
			inheritanceAdaptation.println("Entries are generated using an adaptation of PrintWriter;\n" +
					"the method 'println() has been modified to start with a newline.");
			inheritanceAdaptation.println(receiptDTO.toString());
			inheritanceAdaptation.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
