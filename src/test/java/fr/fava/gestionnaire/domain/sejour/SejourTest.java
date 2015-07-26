package fr.fava.gestionnaire.domain.sejour;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
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
    public void testNombreJoursDateFinMoisDifferents() {
        Sejour sejour = new Sejour(toDate("01/01/2015"), toDate("02/02/2015"));
        Assert.assertEquals(32, sejour.nombreJours());
    }

    @Test
    public void testNombreJoursDateFinReelle() {
        Sejour sejour = new Sejour(toDate("01/01/2015"), toDate("10/01/2015"));
        sejour.setDateFinReelle(toDate("08/01/2015"));
        Assert.assertEquals(7, sejour.nombreJours());
    }

    @Test
    public void testNombreJoursAnnule() {
        Sejour sejour = new Sejour(toDate("01/01/2015"), toDate("10/01/2015"));
        sejour.setDateAnnulation(toDate("31/12/2015"));
        Assert.assertEquals(0, sejour.nombreJours());
    }

    @Test
    public void testStatutAVenir() {
        Sejour sejour = new Sejour(toDate("02/01/2015"), toDate("10/01/2015"));
        Date dateDuJour = toDate("01/01/2015");
        Optional statut = sejour.statut(dateDuJour);
        Assert.assertTrue(statut.isPresent());
        Assert.assertEquals(StatutSejour.A_VENIR, statut.get());
    }

    @Test
    public void testStatutAnnule() {
        Date dateDuJour = toDate("01/01/2015");
        Sejour sejour = new Sejour(toDate("02/01/2015"), toDate("10/01/2015"));
        sejour.setDateAnnulation(toDate("31/12/2014"));
        Optional statut = sejour.statut(dateDuJour);
        Assert.assertTrue(statut.isPresent());
        Assert.assertEquals(StatutSejour.ANNULE, statut.get());
    }

    @Test
    public void testStatutEnCours() {
        Date dateDuJour = toDate("01/01/2015");
        Sejour sejour = new Sejour(toDate("01/01/2015"), toDate("10/01/2015"));
        Optional statut = sejour.statut(dateDuJour);
        Assert.assertTrue(statut.isPresent());
        Assert.assertEquals(StatutSejour.EN_COURS, statut.get());
    }

    @Test
    public void testStatutTermine() {
        Date dateDuJour = toDate("10/01/2015");
        Sejour sejour = new Sejour(toDate("01/01/2015"), toDate("10/01/2015"));
        Optional statut = sejour.statut(dateDuJour);
        Assert.assertTrue(statut.isPresent());
        Assert.assertEquals(StatutSejour.TERMINE, statut.get());
    }

    @Test
    public void testStatutTerminePrematurement() {
        Date dateDuJour = toDate("09/01/2015");
        Sejour sejour = new Sejour(toDate("01/01/2015"), toDate("10/01/2015"));
        sejour.setDateFinReelle(toDate("08/01/2015"));
        Optional statut = sejour.statut(dateDuJour);
        Assert.assertTrue(statut.isPresent());
        Assert.assertEquals(StatutSejour.TERMINE_PREMATUREMENT, statut.get());
    }

}
