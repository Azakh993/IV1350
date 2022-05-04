package se.kth.iv1350.posSystem.integration;

import se.kth.iv1350.posSystem.model.SaleDTO;
import se.kth.iv1350.posSystem.utilities.Amount;

class CashRegister {
    private Amount cashInRegister;

    CashRegister(Amount cashAtStartup) {
        this.cashInRegister = cashAtStartup;
    }

    Amount getCashInRegister() {
        return this.cashInRegister;
    }

    void setCashInRegister(SaleDTO saleDTO) {
        this.cashInRegister = this.cashInRegister.plus(saleDTO.getTotalPrice());
    }
}
