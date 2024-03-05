package se.kth.iv1350.posSystem.integration;

import se.kth.iv1350.posSystem.model.SaleDTO;

import java.util.LinkedList;

class ExternalAccountingSystem {
    private final LinkedList<SaleDTO> paymentRecords = new LinkedList<>();

    void setPaymentRecords(SaleDTO saleDTO) {
        this.paymentRecords.add(saleDTO);
    }

    LinkedList<SaleDTO> getpaymentRecords() {
        return this.paymentRecords;
    }
}
