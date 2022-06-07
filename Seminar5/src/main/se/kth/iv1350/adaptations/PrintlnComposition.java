package se.kth.iv1350.adaptations;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Adaptation of PrintWriter via composition; string printed using 'println' starts and ends
 * with a newline.
 */
public class PrintlnComposition {
	private final static String FILE_PATH = "Seminar5/textFiles/printlnViaComposition.txt";
	private final PrintWriter printWriter;

	/**
	 * Creates a new class instance. Instantiates <code>PrintWriter</code> using <code>FileWriter</code>
	 * and stores the reference.
	 * @throws IOException If the file at specified <code>FILE_PATH</code> could not be created
	 */
	public PrintlnComposition() throws IOException{
		printWriter = new PrintWriter(new FileWriter(FILE_PATH, true), true);
	}

	/**
	 * Prints out a newline before and after <code>stringToPrint</code>
	 * @param stringToPrint The string to printout
	 */
	public void println(String stringToPrint) {
		printWriter.println("\n" + stringToPrint);
	}
}
