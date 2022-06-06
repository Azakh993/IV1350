package se.kth.iv1350.posSystem.integration;

import se.kth.iv1350.posSystem.dto.ReceiptDTO;
import se.kth.iv1350.posSystem.utilities.Task2.PrintlnComposition;
import se.kth.iv1350.posSystem.utilities.Task2.PrintlnInheritance;

import java.io.IOException;

class ReceiptPrinter {
	private static final ReceiptPrinter RECEIPT_PRINTER = new ReceiptPrinter();

	static ReceiptPrinter getReceiptPrinter() {
		return RECEIPT_PRINTER;
	}

	void printReceipt(ReceiptDTO receiptDTO) {
		try {
			PrintlnComposition compositionAdaptation = new PrintlnComposition();
			compositionAdaptation.println("String surrounded by newline; adaptation via composition");
			compositionAdaptation.println(receiptDTO.toString());
			PrintlnInheritance inheritanceAdaptation = new PrintlnInheritance();
			inheritanceAdaptation.println("String surrounded by newline; adaptation via inheritance");
			inheritanceAdaptation.println(receiptDTO.toString());
			inheritanceAdaptation.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
