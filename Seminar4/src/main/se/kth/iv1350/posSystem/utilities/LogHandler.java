package se.kth.iv1350.posSystem.utilities;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class generates and adds exceptions to the log.
 */
public class LogHandler {
    private final static String LOG_FILE_PATH = "Seminar4/textFiles/log.txt";
    private PrintWriter log;

    /**
     * Generates an empty log file
     *
     * @throws IOException if the file could not be created.
     */
    public LogHandler() {
        try {
            this.log = new PrintWriter(new FileWriter(LOG_FILE_PATH), true);
        } catch (IOException exception) {
            System.out.println("File could not be created!");
            exception.printStackTrace();
        }
    }

    /**
     * Adds the formatted log entry to the log file
     *
     * @param formattedLogEntry The formatted log entry
     */
    public void addExceptionToLog(String formattedLogEntry) {
        log.println(formattedLogEntry);
        log.close();
    }
}