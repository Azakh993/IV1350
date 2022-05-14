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
     *
     * @throws IOException if the file could not be created.
     */
    public FileLogger(String filePath) {
        try {
            this.textFile = new PrintWriter(new FileWriter(filePath), true);
        } catch (IOException exception) {
            System.out.println("File could not be created!");
            exception.printStackTrace();
        }
    }

    /**
     * Adds the formatted log entry to the text file
     *
     * @param formattedLogEntry The formatted log entry
     */
    public void addEntryToLog(String formattedLogEntry) {
        textFile.println(formattedLogEntry);
        textFile.close();
    }
}