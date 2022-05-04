package se.kth.iv1350.posSystem.integration;

import se.kth.iv1350.posSystem.model.SaleDTO;

class ReceiptPrinter {
    void printReceipt(SaleDTO saleDTO) {
        System.out.println(saleDTO.toString());
    }
}
