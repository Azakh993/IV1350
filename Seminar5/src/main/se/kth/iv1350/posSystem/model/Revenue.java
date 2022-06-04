package se.kth.iv1350.posSystem.model;

import se.kth.iv1350.posSystem.utilities.Amount;
import se.kth.iv1350.posSystem.utilities.TimeAndDate;

/**
 * Responsible for tracking revenue generated since program startup.
 */
public abstract class Revenue implements PaymentObserver {
	private Amount totalRevenue = new Amount(0);
	private Amount numberOfTransactions = new Amount(0);
	private String timeAndDateOfLastSale;

	/**
	 * The method implemented from <code>PaymentObserver</code>. Updates <code>totalRevenue</code> with
	 * <code>revenueToAdd</code> after conclusion of each sale.
	 * @param revenueToAdd The revenue to add to <code>totalRevenue</code>>
	 */
	public void setAmountPaidAndChange(Amount revenueToAdd) {
		setTimeAndDateOfLastSale();
		updateTotalRevenue(revenueToAdd);
		updateNumberOfTransactions();
		outputUpdatedRevenue();
	}

	private void setTimeAndDateOfLastSale() {
		this.timeAndDateOfLastSale = new TimeAndDate().getTimeAndDate();
	}

	private void updateTotalRevenue(Amount revenueToAdd) {
		this.totalRevenue = totalRevenue.plus(revenueToAdd);
	}

	private void updateNumberOfTransactions() {
		this.numberOfTransactions = numberOfTransactions.plus(new Amount(1));
	}
	private void outputUpdatedRevenue() {
		try {
			outputTotalRevenue(timeAndDateOfLastSale, totalRevenue, numberOfTransactions);
		} catch (Exception exception) {
			handleException(exception);
		}
	}

	protected abstract void outputTotalRevenue(String timeAndDateOfLastSale, Amount totalRevenue,
	                                           Amount numberOfTransactions) throws Exception;

	protected abstract void handleException(Exception exception);
}
