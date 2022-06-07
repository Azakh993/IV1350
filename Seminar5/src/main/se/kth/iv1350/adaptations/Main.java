package se.kth.iv1350.adaptations;

import java.io.IOException;

public class Main {
	public static void main(String[] args) {

		String generalDescription = "Normally, println only adds a newline after the provided string.\n" +
		                            "The adaptation, however, adds a newline before each string as well.";

		try {
			PrintlnComposition composition = new PrintlnComposition();

			composition.println("This is the output of the 'println' method adaptation via Composition");
			composition.println(generalDescription);

			PrintlnInheritance inheritance = new PrintlnInheritance();
			inheritance.println("This is the output of the 'println' method adaptation via Inheritance");
			inheritance.println(generalDescription);

			inheritance.close();

		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
