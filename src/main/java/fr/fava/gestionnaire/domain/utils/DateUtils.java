package fr.fava.gestionnaire.domain.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author paoesco
 */
public final class DateUtils {

    private DateUtils() {
    }

    public static boolean between(Date date, Date minInclusive, Date maxExclusive) {
        Date working = removeTime(date);
        Date workingMin = removeTime(minInclusive);
        Date workingMax = removeTime(maxExclusive);
        return workingMin.getTime() <= working.getTime() && working.getTime() < workingMax.getTime();
    }

    public static boolean before(Date d1, Date d2) {
        Date workingD1 = removeTime(d1);
        Date workingD2 = removeTime(d2);
        return workingD1.compareTo(workingD2) < 0;
    }

    public static boolean after(Date d1, Date d2) {
        Date workingD1 = removeTime(d1);
        Date workingD2 = removeTime(d2);
        return workingD1.compareTo(workingD2) >= 0;
    }

    public static LocalDate toLocalDate(Date date) {
        if (date == null) {
            return null;
        }
        if (date instanceof java.sql.Date) {
            return ((java.sql.Date) date).toLocalDate();
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

}
