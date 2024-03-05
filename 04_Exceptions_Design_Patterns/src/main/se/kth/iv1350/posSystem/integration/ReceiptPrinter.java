package se.kth.iv1350.posSystem.integration;

import se.kth.iv1350.posSystem.dto.ReceiptDTO;

class ReceiptPrinter {
    private static final ReceiptPrinter RECEIPT_PRINTER = new ReceiptPrinter();

    static ReceiptPrinter getReceiptPrinter() {
        return RECEIPT_PRINTER;
    }

    void printReceipt(ReceiptDTO receiptDTO) {
        System.out.println(receiptDTO.toString());
    }
}
