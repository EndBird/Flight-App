package group730.bookingclient.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A utility used to parse or stringify a date from/to a string/Date object.
 */
public class DateTimeUtils {

    /**
     * Converts a string containing a Date to a Date object.
     *
     * @param dateAsString String containing a date in the format 'yyyy-MM-dd'.
     * @return Date object representing the date.
     */
    public static Date convertToDate(String dateAsString) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateAsString);
        } catch (ParseException e) {

        }

        return null;
    }

    /**
     * Converts a string containing a Date to a Date object.
     *
     * @param dateAsString string containing a date in the format
     *                     'yyyy-MM-dd HH:mm'.
     * @return Date object representing the date and time.
     */
    public static Date convertToDateTime(String dateAsString) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateAsString);
        } catch (ParseException e) {

        }

        return null;
    }

    /**
     * Converts a Date object to its string representation.
     *
     * @param date Date object representing the date.
     * @return string containing a date in the format 'yyyy-MM-dd'.
     */
    public static String convertDateToString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    /**
     * Converts a Date object to its string representation.
     *
     * @param date Date object representing the date and time.
     * @return string containing a date in the format 'yyyy-MM-dd HH:mm'.
     */
    public static String convertDateTimeToString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
    }

    /**
     * Converts a date represented by the number of milliseconds since epoch
     * to a string representation.
     *
     * @param milliseconds the number of milliseconds since epoch.
     * @return string containing a date in the format 'HH:mm'.
     */
    public static String convertTimeToString(long milliseconds) {
        int hours = (int) ((milliseconds / (1000 * 60 * 60)) % 24);
        int minutes = (int) ((milliseconds / (1000 * 60)) % 60);
        return String.format("%02d:%02d", hours, minutes);
    }
}
