package fr.famivac.gestionnaire.sejours.control;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author paoesco
 */
public class BilanDTO {

    private Date dateDebut;
    private Date dateFin;
    private Integer totalNombreJourneesVacances;
    private BigDecimal totalForfait;
    private BigDecimal totalFraisDossier;
    private BigDecimal totalFraisVoyage;
    private BigDecimal totalFraisPensionFamille;

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Integer getTotalNombreJourneesVacances() {
        return totalNombreJourneesVacances;
    }

    public void setTotalNombreJourneesVacances(Integer totalNombreJourneesVacances) {
        this.totalNombreJourneesVacances = totalNombreJourneesVacances;
    }

    public BigDecimal getTotalForfait() {
        return totalForfait;
    }

    public void setTotalForfait(BigDecimal totalForfait) {
        this.totalForfait = totalForfait;
    }

    public BigDecimal getTotalFraisDossier() {
        return totalFraisDossier;
    }

    public void setTotalFraisDossier(BigDecimal totalFraisDossier) {
        this.totalFraisDossier = totalFraisDossier;
    }

    public BigDecimal getTotalFraisVoyage() {
        return totalFraisVoyage;
    }

    public void setTotalFraisVoyage(BigDecimal totalFraisVoyage) {
        this.totalFraisVoyage = totalFraisVoyage;
    }

    public BigDecimal getTotalFraisSejour() {
        return getTotalForfait().add(getTotalFraisDossier()).add(getTotalFraisVoyage());
    }

    public BigDecimal getTotalFraisPensionFamille() {
        return totalFraisPensionFamille;
    }

    public void setTotalFraisPensionFamille(BigDecimal totalFraisPensionFamille) {
        this.totalFraisPensionFamille = totalFraisPensionFamille;
    }

}
