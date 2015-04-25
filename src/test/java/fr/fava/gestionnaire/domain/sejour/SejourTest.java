package fr.fava.gestionnaire.domain.sejour;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author paoesco
 */
public class SejourTest {

    private Date toDate(String sDate) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
        } catch (ParseException ex) {
            Assert.fail("Erreur lors de la conversion de la date : " + ex.getLocalizedMessage());
        }
        return null;
    }

    @Test
    public void testNombreJoursDateFin() {
        Sejour sejour = new Sejour(toDate("01/01/2015"), toDate("10/01/2015"));
        Assert.assertEquals(9, sejour.nombreJours());
    }

    @Test
    public void testNombreJoursDateFinReelle() {
        Sejour sejour = new Sejour(toDate("01/01/2015"), toDate("10/01/2015"));
        sejour.setDateFinReelle(toDate("08/01/2015"));
        Assert.assertEquals(7, sejour.nombreJours());
    }

}
