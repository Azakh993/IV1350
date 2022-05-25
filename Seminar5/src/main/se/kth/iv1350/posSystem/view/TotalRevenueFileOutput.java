package se.kth.iv1350.posSystem.view;

import se.kth.iv1350.posSystem.model.Revenue;
import se.kth.iv1350.posSystem.utilities.Amount;
import se.kth.iv1350.posSystem.utilities.FileLogger;

/**
 * Class that shows the revenue generated by sales and outputs the current total revenue to a text file.
 */
public class TotalRevenueFileOutput extends Revenue {
	private final static String REVENUE_FILE_PATH = "Seminar5/textFiles/totalRevenue.txt";
	private final OutputFormatter outputFormatter;
	private final FileLogger fileLogger = new FileLogger(REVENUE_FILE_PATH);

	TotalRevenueFileOutput(OutputFormatter outputFormatter) {
		this.outputFormatter = outputFormatter;
	}

	@Override
	protected void outputTotalRevenue(String timeAndDateOfLastSale, Amount totalRevenue) {
		String logEntry = "Revenue at " + timeAndDateOfLastSale + ": " + totalRevenue;
		fileLogger.addEntryToLog(logEntry);
	}

	@Override
	protected void handleException(Exception exception) {
		outputFormatter.exceptionLogEntryFormatter(exception);
	}
}