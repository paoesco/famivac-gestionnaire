package fr.famivac.gestionnaire.domains.sejours.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

/**
 * @author paoesco
 */
public class SejourTest {
    
    private Date toDate(String sDate) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
        } catch (ParseException ex) {
            Assertions.fail("Erreur lors de la conversion de la date : " + ex.getLocalizedMessage());
        }
        return null;
    }

    // Tests sur le nombre de jours
    @Test
    public void testNombreJoursDebutMatinEtFinApresMidi() {
        Sejour sejour = new Sejour(toDate("01/01/2015"), PeriodeJournee.MATIN, toDate("10/01/2015"), PeriodeJournee.APRES_MIDI);
        Assertions.assertEquals(10, sejour.nombreJours().intValue());
    }
    
    @Test
    public void testNombreJoursDebutMatinEtFinMatin() {
        Sejour sejour = new Sejour(toDate("01/01/2015"), PeriodeJournee.MATIN, toDate("10/01/2015"), PeriodeJournee.MATIN);
        Assertions.assertEquals(9, sejour.nombreJours().intValue());
    }
    
    @Test
    public void testNombreJoursDebutApresMidiEtFinApresMidi() {
        Sejour sejour = new Sejour(toDate("01/01/2015"), PeriodeJournee.APRES_MIDI, toDate("10/01/2015"), PeriodeJournee.APRES_MIDI);
        Assertions.assertEquals(9, sejour.nombreJours().intValue());
    }
    
    @Test
    public void testNombreJoursDebutApresMidiEtFinMatin() {
        Sejour sejour = new Sejour(toDate("01/01/2015"), PeriodeJournee.APRES_MIDI, toDate("10/01/2015"), PeriodeJournee.MATIN);
        Assertions.assertEquals(8, sejour.nombreJours().intValue());
    }
    
    @Test
    public void testNombreJoursDebutMatinEtFinApresMidiMoisDifferents() {
        Sejour sejour = new Sejour(toDate("01/01/2015"), PeriodeJournee.MATIN, toDate("02/02/2015"), PeriodeJournee.APRES_MIDI);
        Assertions.assertEquals(33, sejour.nombreJours().intValue());
    }
    
    @Test
    public void testNombreJoursDebutApresMidiEtFinApresMidiMoisDifferents() {
        Sejour sejour = new Sejour(toDate("01/01/2015"), PeriodeJournee.APRES_MIDI, toDate("02/02/2015"), PeriodeJournee.APRES_MIDI);
        Assertions.assertEquals(32, sejour.nombreJours().intValue());
    }
    
    @Test
    public void testNombreJoursAnnuleDebutMatinFinApresMidi() {
        Sejour sejour = new Sejour(toDate("01/01/2015"), PeriodeJournee.MATIN, toDate("10/01/2015"), PeriodeJournee.APRES_MIDI);
        sejour.setDateAnnulation(toDate("31/12/2015"));
        Assertions.assertEquals(0, sejour.nombreJours().intValue());
    }

    // Tests sur le status
    @Test
    public void testStatutAVenir() {
        Sejour sejour = new Sejour(toDate("02/01/2015"), PeriodeJournee.MATIN, toDate("10/01/2015"), PeriodeJournee.APRES_MIDI);
        Date dateDuJour = toDate("01/01/2015");
        Optional statut = sejour.statut(dateDuJour);
        Assertions.assertTrue(statut.isPresent());
        Assertions.assertEquals(StatutSejour.A_VENIR, statut.get());
    }
    
    @Test
    public void testStatutAnnule() {
        Date dateDuJour = toDate("01/01/2015");
        Sejour sejour = new Sejour(toDate("02/01/2015"), PeriodeJournee.MATIN, toDate("10/01/2015"), PeriodeJournee.APRES_MIDI);
        sejour.setDateAnnulation(toDate("31/12/2014"));
        Optional statut = sejour.statut(dateDuJour);
        Assertions.assertTrue(statut.isPresent());
        Assertions.assertEquals(StatutSejour.ANNULE, statut.get());
    }
    
    @Test
    public void testStatutEnCours() {
        Date dateDuJour = toDate("01/01/2015");
        Sejour sejour = new Sejour(toDate("01/01/2015"), PeriodeJournee.MATIN, toDate("10/01/2015"), PeriodeJournee.APRES_MIDI);
        Optional statut = sejour.statut(dateDuJour);
        Assertions.assertTrue(statut.isPresent());
        Assertions.assertEquals(StatutSejour.EN_COURS, statut.get());
    }
    
    @Test
    public void testStatutTermine() {
        Date dateDuJour = toDate("10/01/2015");
        Sejour sejour = new Sejour(toDate("01/01/2015"), PeriodeJournee.MATIN, toDate("10/01/2015"), PeriodeJournee.APRES_MIDI);
        Optional statut = sejour.statut(dateDuJour);
        Assertions.assertTrue(statut.isPresent());
        Assertions.assertEquals(StatutSejour.TERMINE, statut.get());
    }
    
    @Test
    public void testStatutTerminePrematurement() {
        Date dateDuJour = toDate("10/01/2015");
        Sejour sejour = new Sejour(toDate("01/01/2015"), PeriodeJournee.MATIN, toDate("10/01/2015"), PeriodeJournee.APRES_MIDI);
        sejour.setMotifFinSejour("Changement");
        Optional statut = sejour.statut(dateDuJour);
        Assertions.assertTrue(statut.isPresent());
        Assertions.assertEquals(StatutSejour.TERMINE_PREMATUREMENT, statut.get());
    }
    
}
