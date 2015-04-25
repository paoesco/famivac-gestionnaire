package fr.fava.gestionnaire.application.sejours;

import java.util.Date;
import javax.validation.constraints.NotNull;

/**
 *
 * @author paoesco
 */
public class AjouterSejourDTO {

    @NotNull
    private Long familleId;

    @NotNull
    private Long enfantId;

    @NotNull
    private Date dateDebut;

    @NotNull
    private Date dateFin;

    public Long getFamilleId() {
        return familleId;
    }

    public void setFamilleId(Long familleId) {
        this.familleId = familleId;
    }

    public Long getEnfantId() {
        return enfantId;
    }

    public void setEnfantId(Long enfantId) {
        this.enfantId = enfantId;
    }

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
}
