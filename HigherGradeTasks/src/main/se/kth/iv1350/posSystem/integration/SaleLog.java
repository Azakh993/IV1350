package se.kth.iv1350.posSystem.integration;

import se.kth.iv1350.posSystem.dto.ReceiptDTO;

import java.util.LinkedList;

class SaleLog {
	private static final SaleLog SALE_LOG = new SaleLog();
	private final LinkedList<ReceiptDTO> transactionsList = new LinkedList<>();

	static SaleLog getSaleLog() {
		return SALE_LOG;
	}

	void setSaleInstance(ReceiptDTO receiptDTO) {
		this.transactionsList.add(receiptDTO);
	}

	LinkedList<ReceiptDTO> getTransactionsList() {
		return this.transactionsList;
	}
}
