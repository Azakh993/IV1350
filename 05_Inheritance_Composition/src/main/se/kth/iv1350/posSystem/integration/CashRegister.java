package se.kth.iv1350.posSystem.integration;

import se.kth.iv1350.posSystem.dto.ReceiptDTO;
import se.kth.iv1350.posSystem.utilities.Amount;

class CashRegister {
	private Amount cashInRegister;

	CashRegister(Amount cashAtStartup) {
		this.cashInRegister = cashAtStartup;
	}

	Amount getCashInRegister() {
		return cashInRegister;
	}

	void setCashInRegister(ReceiptDTO receiptDTO) {
		this.cashInRegister = cashInRegister.plus(receiptDTO.getTotalPrice());
	}
}
