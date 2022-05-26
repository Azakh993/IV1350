package se.kth.iv1350.posSystem.utilities.Task2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PrintWriterAdaptationViaInheritance extends PrintWriter {
	private final static String FILE_PATH = "Seminar5/textFiles/receiptViaInheritanceAdaptation.txt";


	public PrintWriterAdaptationViaInheritance() throws IOException{
		super(new FileWriter(FILE_PATH, true), true);
	}

	@Override
	public void println(String stringToPrint) {
		super.println("\n" + stringToPrint);
	}
}
