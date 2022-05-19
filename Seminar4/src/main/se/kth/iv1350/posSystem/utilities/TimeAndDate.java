package se.kth.iv1350.posSystem.utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Responsible for generating time and date in a specific format
 */
public class TimeAndDate {
    private final String timeAndDate;

    /**
     * Creates an instance, and stores the time and date of creation
     */
    public TimeAndDate() {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();
        this.timeAndDate = dateTimeFormat.format(now);
    }

    /**
     * Getter function that returns the time and date
     *
     * @return The <code>timeAndDate</code>
     */
    public String getTimeAndDate() {
        return this.timeAndDate;
    }
}
