package se.kth.iv1350.posSystem.integration;

import se.kth.iv1350.posSystem.model.SaleDTO;

import java.util.LinkedList;

class SaleLog {
    private static final SaleLog SALE_LOG = new SaleLog();
    private final LinkedList<SaleDTO> transactionsList = new LinkedList<>();

    static SaleLog getSaleLog () {
        return SALE_LOG;
    }
    void setSaleInstance(SaleDTO saleDTO) {
        this.transactionsList.add(saleDTO);
    }

    LinkedList<SaleDTO> getTransactionsList() {
        return this.transactionsList;
    }
}
