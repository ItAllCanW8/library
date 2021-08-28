package by.epamtc.library.util;

import java.time.format.DateTimeFormatter;

/**
 * Class provides formatter for DateTime.
 *
 * @author Artur Mironchik
 */
public final class DateTimeHelper {
    private DateTimeHelper(){}

    /**
     * The constant formatter.
     */
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
}
