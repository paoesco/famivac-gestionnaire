package fr.famivac.gestionnaire.interfaces.web.sejours;

import fr.famivac.gestionnaire.sejours.entity.Sejour;
import java.math.BigDecimal;

/**
 * @author paoesco
 */
public class BilanSejour {

    private Sejour sejour;

    public BilanSejour(Sejour sejour) {
        this.sejour = sejour;
    }

    public BigDecimal fraisSejour() {
        return new BigDecimal(sejour.nombreJours()).multiply(sejour.getFraisSejourJournalier());
    }

    public BigDecimal fraisDossier() {
        return sejour.getFraisDossier();
    }

}
