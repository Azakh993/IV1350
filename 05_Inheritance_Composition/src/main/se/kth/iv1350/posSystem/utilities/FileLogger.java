package se.kth.iv1350.posSystem.utilities;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class generates and adds content to a file
 */
public class FileLogger {
	private PrintWriter textFile;

	/**
	 * Generates an empty text file
	 */
	public FileLogger(String filePath) {
		try {
			textFile = new PrintWriter(new FileWriter(filePath, true), true);
		} catch (IOException exception) {
			System.out.println("File could not be created!");
			addEntryToLog(exception.getMessage());
		}
	}

	/**
	 * Adds the formatted log entry to the text file
	 * @param logEntry The formatted log entry
	 */
	public void addEntryToLog(String logEntry) {
		textFile.println(logEntry);
	}
}