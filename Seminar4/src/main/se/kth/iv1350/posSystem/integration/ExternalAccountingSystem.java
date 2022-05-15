package se.kth.iv1350.posSystem.integration;

import se.kth.iv1350.posSystem.model.SaleDTO;

import java.util.LinkedList;

class ExternalAccountingSystem {
    private static final ExternalAccountingSystem EXTERNAL_ACCOUNTING_SYSTEM = new ExternalAccountingSystem();
    private final LinkedList<SaleDTO> paymentRecords = new LinkedList<>();

    static ExternalAccountingSystem getExternalAccountingSystem () {
        return EXTERNAL_ACCOUNTING_SYSTEM;
    }
    LinkedList<SaleDTO> getPaymentRecords() {
        return this.paymentRecords;
    }

    void setPaymentRecords(SaleDTO saleDTO) {
        this.paymentRecords.add(saleDTO);
    }
}
