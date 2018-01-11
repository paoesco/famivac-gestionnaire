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

    public BigDecimal forfait() {
        return new BigDecimal(sejour.nombreJours()).multiply(sejour.getForfaitJournalier());
    }

    public BigDecimal fraisDossier() {
        return sejour.getFraisDossier();
    }

    public BigDecimal fraisVoyage() {
        return sejour.getFraisVoyage();
    }

    public BigDecimal fraisSejour() {
        return forfait().add(fraisDossier()).add(fraisVoyage());
    }

    public BigDecimal fraisPensionFamille() {
        return new BigDecimal(sejour.nombreJours()).multiply(sejour.getFraisPensionFamilleJournalier());
    }

}
