package se.kth.iv1350.posSystem.view;

import se.kth.iv1350.posSystem.utilities.TimeAndDate;

import java.io.PrintWriter;
import java.io.StringWriter;

class ExceptionFormatter {

    String exceptionMessageFormatter(Exception exception) {
        return ">> Error: " + exception.getMessage() + " <<";
    }

    String exceptionLogEntryFormatter(Exception exception) {
        StringBuilder logEntry = new StringBuilder();
        TimeAndDate timeAndDateOfException = new TimeAndDate();
        logEntry.append(timeAndDateOfException.getTimeAndDate());
        logEntry.append("\n");
        StringWriter stackTraceAsString = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stackTraceAsString);
        exception.printStackTrace(printWriter);
        logEntry.append(stackTraceAsString);
        logEntry.append("\n\n");

        return logEntry.toString();
    }
}