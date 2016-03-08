package fr.famivac.gestionnaire.sejours.control;

import fr.famivac.gestionnaire.familles.control.FamilleDTO;
import fr.famivac.gestionnaire.enfants.control.EnfantDTO;
import java.util.Date;
import javax.validation.constraints.NotNull;

/**
 *
 * @author paoesco
 */
public class AjouterSejourDTO {

    @NotNull
    private FamilleDTO famille;

    @NotNull
    private EnfantDTO enfant;

    @NotNull
    private Date dateDebut;

    @NotNull
    private Date dateFin;

    public FamilleDTO getFamille() {
        return famille;
    }

    public void setFamille(FamilleDTO famille) {
        this.famille = famille;
    }

    public EnfantDTO getEnfant() {
        return enfant;
    }

    public void setEnfant(EnfantDTO enfant) {
        this.enfant = enfant;
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
