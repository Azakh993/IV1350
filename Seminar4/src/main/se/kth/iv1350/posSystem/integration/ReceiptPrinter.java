package se.kth.iv1350.posSystem.integration;

import se.kth.iv1350.posSystem.model.SaleDTO;

class ReceiptPrinter {
    void printReceipt(SaleDTO saleDTO) throws ExternalSystemException {
        if(saleDTO.getLastRegisteredItem().getItemID().equals("404X"))
            throw new ExternalSystemException(this);
        // Code here for printing receipt
    }
}
