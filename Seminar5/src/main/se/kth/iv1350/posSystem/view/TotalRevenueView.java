package se.kth.iv1350.posSystem.view;

import se.kth.iv1350.posSystem.model.Revenue;
import se.kth.iv1350.posSystem.utilities.Amount;

/**
 * Class that shows the revenue generated by sales and prints out the current total revenue.
 */
public class TotalRevenueView extends Revenue {

	protected void outputTotalRevenue(String timeAndDateOfLastSale, Amount totalRevenue) {
		System.out.println("\n\t* * * * * * * * * * * * * * * * * * *");
		System.out.println("\tRevenue at " + timeAndDateOfLastSale + ": " + totalRevenue);
		System.out.println("\t* * * * * * * * * * * * * * * * * * *\n");
	}

	protected void handleException(Exception exception) {
		System.out.println("\n\t* * * * * * * * * * * * * * * * * * *");
		System.out.println("\tError: Could not get total revenue");
		System.out.println("\t* * * * * * * * * * * * * * * * * * *");
	}
}
