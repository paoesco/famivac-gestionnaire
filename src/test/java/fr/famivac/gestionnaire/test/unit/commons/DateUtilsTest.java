package fr.famivac.gestionnaire.test.unit.commons;

import fr.famivac.gestionnaire.commons.entity.utils.DateUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author pescobar
 */
public class DateUtilsTest {

    private Date toDate(String s) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            return sdf.parse(s);
        } catch (ParseException ex) {
            Logger.getLogger(DateUtilsTest.class.getName()).log(Level.SEVERE, null, ex);
            Assert.fail();
            return null;
        }
    }

    @Test
    public void testBetweenMinInclusive() {
        Date toTest = toDate("01/01/2015 01:00");
        Date min = toDate("01/01/2015 02:00");
        Date max = toDate("31/01/2015 23:59");
        boolean result = DateUtils.between(toTest, min, max);
        Assert.assertTrue(result);
    }

    @Test
    public void testBetweenMaxExclusive() {
        Date toTest = toDate("31/01/2015 01:00");
        Date min = toDate("01/01/2015 02:00");
        Date max = toDate("31/01/2015 03:59");
        boolean result = DateUtils.between(toTest, min, max);
        Assert.assertFalse(result);
    }

    @Test
    public void testBeforeTrue() {
        Date d1 = toDate("01/01/2015 02:00");
        Date d2 = toDate("31/01/2015 03:59");
        boolean result = DateUtils.before(d1, d2);
        Assert.assertTrue(result);
    }

    @Test
    public void testBeforeFalse() {
        Date d1 = toDate("01/01/2015 02:00");
        Date d2 = toDate("31/01/2014 03:59");
        boolean result = DateUtils.before(d1, d2);
        Assert.assertFalse(result);
    }

    @Test
    public void testBeforeDateEqualsFalse() {
        Date d1 = toDate("01/01/2015 02:00");
        Date d2 = toDate("01/01/2015 03:59");
        boolean result = DateUtils.before(d1, d2);
        Assert.assertFalse(result);
    }

    @Test
    public void testAfterTrue() {
        Date d1 = toDate("31/01/2015 03:59");
        Date d2 = toDate("01/01/2015 02:00");
        boolean result = DateUtils.after(d1, d2);
        Assert.assertTrue(result);
    }

    @Test
    public void testAfterFalse() {
        Date d1 = toDate("31/01/2014 03:59");
        Date d2 = toDate("01/01/2015 02:00");
        boolean result = DateUtils.after(d1, d2);
        Assert.assertFalse(result);
    }

    @Test
    public void testAfterDateEqualsTrue() {
        Date d1 = toDate("01/01/2015 03:59");
        Date d2 = toDate("01/01/2015 02:00");
        boolean result = DateUtils.after(d1, d2);
        Assert.assertTrue(result);
    }

    @Test
    public void testToLocaleDate() {
        Date toTest = toDate("31/01/2015 01:00");
        LocalDate result = DateUtils.toLocalDate(toTest);
        Assert.assertEquals("2015-01-31", result.format(DateTimeFormatter.ISO_DATE));
    }

}
