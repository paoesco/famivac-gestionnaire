package fr.famivac.gestionnaire.sejours.control;

import fr.famivac.gestionnaire.sejours.entity.PeriodeJournee;
import fr.famivac.gestionnaire.sejours.entity.Sejour;
import fr.famivac.gestionnaire.sejours.entity.SejourRepository;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author paoesco
 */
@RunWith(MockitoJUnitRunner.class)
public class SejourServiceTest {

    @Mock
    private SejourRepository repository;

    @InjectMocks
    private SejourService service;

    @Test
    public void testGetBilanSurLaPeriode() {
        // GIVEN
        Date dateDebut = toDate("01/01/2018");
        Date dateFin = toDate("31/01/2018");
        initSejours();
        
        // WHEN
        BilanDTO bilan = service.getBilanSurLaPeriode(dateDebut, dateFin);
        
        // THEN
        Assert.assertEquals(dateDebut, bilan.getDateDebut());
        Assert.assertEquals(dateFin, bilan.getDateFin());
        Assert.assertEquals(62, bilan.getTotalNombreJourneesVacances().intValue());
        Assert.assertEquals(50, bilan.getTotalFraisDossier().intValue());
        Assert.assertEquals(2170, bilan.getTotalFraisSejour().intValue());
        Assert.assertEquals(90, bilan.getTotalFraisVoyage().intValue());
    }

    private void initSejours() {
        List<Sejour> sejours = new ArrayList<>();
        Sejour sejour1 = new Sejour(
                toDate("01/01/2018"),
                PeriodeJournee.MATIN,
                toDate("31/01/2018"),
                PeriodeJournee.APRES_MIDI)
                .withFraisDossier(BigDecimal.valueOf(10))
                .withFraisSejourJournalier(BigDecimal.valueOf(20))
                .withFraisVoyage(BigDecimal.valueOf(30));
        sejours.add(sejour1);
        Sejour sejour2 = new Sejour(
                toDate("01/01/2018"),
                PeriodeJournee.MATIN,
                toDate("31/01/2018"),
                PeriodeJournee.APRES_MIDI)
                .withFraisDossier(BigDecimal.valueOf(40))
                .withFraisSejourJournalier(BigDecimal.valueOf(50))
                .withFraisVoyage(BigDecimal.valueOf(60));
        sejours.add(sejour2);
        Mockito.when(repository.getSejoursTerminesDansLaPeriode(Mockito.any(Date.class), Mockito.any(Date.class))).thenReturn(sejours);
    }

    private Date toDate(String source) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.parse(source);
        } catch (ParseException ex) {
            Assert.fail(ex.getMessage());
            return null;
        }
    }

}
