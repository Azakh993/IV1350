package se.kth.iv1350.posSystem.integration;

import se.kth.iv1350.posSystem.model.SaleDTO;

class ReceiptPrinter {
    private static final ReceiptPrinter RECEIPT_PRINTER = new ReceiptPrinter();

    static ReceiptPrinter getReceiptPrinter () {
        return RECEIPT_PRINTER;
    }
    void printReceipt(SaleDTO saleDTO) {
        // Code here for printing receipt
    }
}
