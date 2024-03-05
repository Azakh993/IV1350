package se.kth.iv1350.adaptations;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Adaptation of PrintWriter via inheritance; string printed using 'println' starts and ends
 * with a newline.
 */
public class PrintlnInheritance extends PrintWriter {
	private final static String FILE_PATH = "Seminar5/textFiles/printlnViaInheritance.txt";

	/**
	 * Creates a new class instance. Instantiates <code>PrintWriter</code> with <code>FileWriter</code>
	 * using a super class constructor.
	 * @throws IOException If the file at specified <code>FILE_PATH</code> could not be created
	 */
	public PrintlnInheritance() throws IOException{
		super(new FileWriter(FILE_PATH, true), true);
	}

	/**
	 * Overrides super class methpd 'println'; before and after printout of <code>stringToPrint</code>,
	 * newline is entered.
	 * @param stringToPrint the string to be printed
	 */
	@Override
	public void println(String stringToPrint) {
		super.println("\n" + stringToPrint);
	}
}
