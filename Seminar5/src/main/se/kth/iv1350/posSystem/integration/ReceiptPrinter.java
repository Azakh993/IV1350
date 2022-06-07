package se.kth.iv1350.posSystem.integration;

import se.kth.iv1350.posSystem.dto.ReceiptDTO;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class ReceiptPrinter {
	private static final ReceiptPrinter RECEIPT_PRINTER = new ReceiptPrinter();
	private final static String FILE_PATH = "Seminar5/textFiles/receipt.txt";
	private PrintWriter printWriter;

	private ReceiptPrinter() {
		try {
			this.printWriter = new PrintWriter( new FileWriter( FILE_PATH, true ), true );
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	static ReceiptPrinter getReceiptPrinter() {
		return RECEIPT_PRINTER;
	}

	void printReceipt(ReceiptDTO receiptDTO) {
		printWriter.println(receiptDTO);
	}
}
