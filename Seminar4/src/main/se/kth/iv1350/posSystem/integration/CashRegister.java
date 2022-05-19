package se.kth.iv1350.posSystem.integration;

import se.kth.iv1350.posSystem.dto.ReceiptDTO;
import se.kth.iv1350.posSystem.utilities.Amount;

class CashRegister {
    private Amount cashInRegister;

    CashRegister(Amount cashAtStartup) {
        this.cashInRegister = cashAtStartup;
    }

    Amount getCashInRegister() {
        return this.cashInRegister;
    }

    void setCashInRegister(ReceiptDTO receiptDTO) {
        this.cashInRegister = this.cashInRegister.plus(receiptDTO.getTotalPrice());
    }
}
