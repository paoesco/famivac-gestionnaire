package fr.famivac.gestionnaire.sejours.entity;

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
    public void testNombreJoursDebutMatinEtDateFin() {
        Sejour sejour = new Sejour(toDate("01/01/2015"), PeriodeJournee.MATIN, toDate("10/01/2015"));
        Assert.assertEquals(10, sejour.nombreJours());
    }

    @Test
    public void testNombreJoursDebutApresMidiEtDateFin() {
        Sejour sejour = new Sejour(toDate("01/01/2015"), PeriodeJournee.APRES_MIDI, toDate("10/01/2015"));
        Assert.assertEquals(9, sejour.nombreJours());
    }

    @Test
    public void testNombreJoursDebutMatinDateFinMoisDifferents() {
        Sejour sejour = new Sejour(toDate("01/01/2015"), PeriodeJournee.MATIN, toDate("02/02/2015"));
        Assert.assertEquals(33, sejour.nombreJours());
    }

    @Test
    public void testNombreJoursDebutApresMidiEtDateFinMoisDifferents() {
        Sejour sejour = new Sejour(toDate("01/01/2015"), PeriodeJournee.APRES_MIDI, toDate("02/02/2015"));
        Assert.assertEquals(32, sejour.nombreJours());
    }

    @Test
    public void testNombreJoursDebutMatinEtDateFinReelle() {
        Sejour sejour = new Sejour(toDate("01/01/2015"), PeriodeJournee.MATIN, toDate("10/01/2015"));
        sejour.setDateFinReelle(toDate("08/01/2015"));
        Assert.assertEquals(8, sejour.nombreJours());
    }

    @Test
    public void testNombreJoursDebutApresMidiEtDateFinReelle() {
        Sejour sejour = new Sejour(toDate("01/01/2015"), PeriodeJournee.APRES_MIDI, toDate("10/01/2015"));
        sejour.setDateFinReelle(toDate("08/01/2015"));
        Assert.assertEquals(7, sejour.nombreJours());
    }

    @Test
    public void testNombreJoursAnnule() {
        Sejour sejour = new Sejour(toDate("01/01/2015"), PeriodeJournee.MATIN, toDate("10/01/2015"));
        sejour.setDateAnnulation(toDate("31/12/2015"));
        Assert.assertEquals(0, sejour.nombreJours());
    }

    @Test
    public void testStatutAVenir() {
        Sejour sejour = new Sejour(toDate("02/01/2015"), PeriodeJournee.MATIN, toDate("10/01/2015"));
        Date dateDuJour = toDate("01/01/2015");
        Optional statut = sejour.statut(dateDuJour);
        Assert.assertTrue(statut.isPresent());
        Assert.assertEquals(StatutSejour.A_VENIR, statut.get());
    }

    @Test
    public void testStatutAnnule() {
        Date dateDuJour = toDate("01/01/2015");
        Sejour sejour = new Sejour(toDate("02/01/2015"), PeriodeJournee.MATIN, toDate("10/01/2015"));
        sejour.setDateAnnulation(toDate("31/12/2014"));
        Optional statut = sejour.statut(dateDuJour);
        Assert.assertTrue(statut.isPresent());
        Assert.assertEquals(StatutSejour.ANNULE, statut.get());
    }

    @Test
    public void testStatutEnCours() {
        Date dateDuJour = toDate("01/01/2015");
        Sejour sejour = new Sejour(toDate("01/01/2015"), PeriodeJournee.MATIN, toDate("10/01/2015"));
        Optional statut = sejour.statut(dateDuJour);
        Assert.assertTrue(statut.isPresent());
        Assert.assertEquals(StatutSejour.EN_COURS, statut.get());
    }

    @Test
    public void testStatutTermine() {
        Date dateDuJour = toDate("10/01/2015");
        Sejour sejour = new Sejour(toDate("01/01/2015"), PeriodeJournee.MATIN, toDate("10/01/2015"));
        Optional statut = sejour.statut(dateDuJour);
        Assert.assertTrue(statut.isPresent());
        Assert.assertEquals(StatutSejour.TERMINE, statut.get());
    }

    @Test
    public void testStatutTerminePrematurement() {
        Date dateDuJour = toDate("09/01/2015");
        Sejour sejour = new Sejour(toDate("01/01/2015"), PeriodeJournee.MATIN, toDate("10/01/2015"));
        sejour.setDateFinReelle(toDate("08/01/2015"));
        Optional statut = sejour.statut(dateDuJour);
        Assert.assertTrue(statut.isPresent());
        Assert.assertEquals(StatutSejour.TERMINE_PREMATUREMENT, statut.get());
    }

}
