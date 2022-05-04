package se.kth.iv1350.posSystem.integration;

import se.kth.iv1350.posSystem.model.SaleDTO;

import java.util.LinkedList;

class SaleLog {
    private LinkedList<SaleDTO> transactionsList = new LinkedList<>();

    void setSaleInstance(SaleDTO saleDTO) {
        this.transactionsList.add(saleDTO);
    }

    LinkedList<SaleDTO> getTransactionsList() {
        return this.transactionsList;
    }
}
