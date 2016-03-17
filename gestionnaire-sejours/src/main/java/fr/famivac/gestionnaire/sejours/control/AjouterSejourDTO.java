package fr.famivac.gestionnaire.sejours.control;

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
    private String familleNom;

    @NotNull
    private String famillePrenom;

    @NotNull
    private Long enfantId;

    @NotNull
    private String enfantNom;

    @NotNull
    private String enfantPrenom;

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

    public String getFamilleNom() {
        return familleNom;
    }

    public void setFamilleNom(String familleNom) {
        this.familleNom = familleNom;
    }

    public String getFamillePrenom() {
        return famillePrenom;
    }

    public void setFamillePrenom(String famillePrenom) {
        this.famillePrenom = famillePrenom;
    }

    public Long getEnfantId() {
        return enfantId;
    }

    public void setEnfantId(Long enfantId) {
        this.enfantId = enfantId;
    }

    public String getEnfantNom() {
        return enfantNom;
    }

    public void setEnfantNom(String enfantNom) {
        this.enfantNom = enfantNom;
    }

    public String getEnfantPrenom() {
        return enfantPrenom;
    }

    public void setEnfantPrenom(String enfantPrenom) {
        this.enfantPrenom = enfantPrenom;
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
