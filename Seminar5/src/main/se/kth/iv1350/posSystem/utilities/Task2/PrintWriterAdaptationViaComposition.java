package se.kth.iv1350.posSystem.utilities.Task2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PrintWriterAdaptationViaComposition {
	private final static String FILE_PATH = "Seminar5/textFiles/receiptViaCompositionAdaptation.txt";
	private final PrintWriter printWriter;

	public PrintWriterAdaptationViaComposition() throws IOException{
		printWriter = new PrintWriter(new FileWriter(FILE_PATH, true), true);
	}

	public void println(String stringToPrint) {
		printWriter.println("\n" + stringToPrint);
	}
}
