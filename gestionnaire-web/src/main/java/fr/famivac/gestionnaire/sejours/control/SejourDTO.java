package fr.famivac.gestionnaire.sejours.control;

import fr.famivac.gestionnaire.sejours.entity.Sejour;
import java.util.Date;

/**
 *
 * @author paoesco
 */
public class SejourDTO {

    private Long id;
    private String famille;
    private Long familleId;
    private String enfant;
    private Long enfantId;
    private Date dateDebut;
    private Date dateFin;
    private String statut;

    public SejourDTO() {
    }

    public SejourDTO(Sejour bean) {
        this.id = bean.getId();

        this.famille = bean.getFamille().getMembreReferent().getPrenom() + " " + bean.getFamille().getMembreReferent().getNom();
        this.familleId = bean.getFamille().getId();
        this.enfant = bean.getEnfant().getPrenom() + " " + bean.getEnfant().getNom();
        this.enfantId = bean.getEnfant().getId();
        this.dateDebut = bean.getDateDebut();
        this.dateFin = bean.getDateFinEffective();
        this.statut = bean.statutDuJour().name();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFamille() {
        return famille;
    }

    public void setFamille(String famille) {
        this.famille = famille;
    }

    public String getEnfant() {
        return enfant;
    }

    public void setEnfant(String enfant) {
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

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

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

}
