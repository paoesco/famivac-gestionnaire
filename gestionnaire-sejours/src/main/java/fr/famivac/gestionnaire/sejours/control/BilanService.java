package fr.famivac.gestionnaire.sejours.control;

import fr.famivac.gestionnaire.sejours.entity.Sejour;
import fr.famivac.gestionnaire.sejours.entity.SejourRepository;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author paoesco
 */
@Stateless
public class BilanService {

    @Inject
    private SejourRepository sejourRepository;

    public BilanDTO getBilan(Date dateDebut, Date dateFin) {
        List<Sejour> sejours = sejourRepository.getSejoursTerminesDansLaPeriode(dateDebut, dateFin);
        BigDecimal totalFraisSejour = BigDecimal.ZERO;
        Integer totalNombreJourneesVacances = 0;
        for (Sejour sejour : sejours) {
            int nombreJourneesVacancesSejour = sejour.nombreJours();
            totalNombreJourneesVacances += nombreJourneesVacancesSejour;
            totalFraisSejour = totalFraisSejour.add(new BigDecimal(nombreJourneesVacancesSejour).multiply(sejour.getFraisSejourJournalier()));
        }
        BilanDTO result = new BilanDTO();
        result.setDateDebut(dateDebut);
        result.setDateFin(dateFin);
        result.setTotalFraisSejour(totalFraisSejour);
        result.setTotalNombreJourneesVacances(totalNombreJourneesVacances.intValue());
        return result;
    }

}
