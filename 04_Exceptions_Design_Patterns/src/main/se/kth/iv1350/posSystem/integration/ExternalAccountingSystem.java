package se.kth.iv1350.posSystem.integration;

import se.kth.iv1350.posSystem.dto.ReceiptDTO;

import java.util.LinkedList;

class ExternalAccountingSystem {
    private static final ExternalAccountingSystem EXTERNAL_ACCOUNTING_SYSTEM = new ExternalAccountingSystem();
    private final LinkedList<ReceiptDTO> paymentRecords = new LinkedList<>();

    static ExternalAccountingSystem getExternalAccountingSystem() {
        return EXTERNAL_ACCOUNTING_SYSTEM;
    }

    LinkedList<ReceiptDTO> getPaymentRecords() {
        return this.paymentRecords;
    }

    void setPaymentRecords(ReceiptDTO receiptDTO) {
        this.paymentRecords.add(receiptDTO);
    }
}
