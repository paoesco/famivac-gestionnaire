package fr.fava.gestionnaire.application.sejour;

import fr.fava.gestionnaire.domain.sejour.Sejour;
import java.util.Date;

/**
 *
 * @author paoesco
 */
public class SejourDTO {

    private Long id;
    private String famille;
    private String enfant;
    private Date dateDebut;
    private Date dateFin;
    private String statut;

    public SejourDTO(Sejour bean) {
        this.id = bean.getId();

        this.famille = bean.getFamille().getMembreReferent().getPrenom() + " " + bean.getFamille().getMembreReferent().getNom();
        this.enfant = bean.getEnfant().getPrenom() + " " + bean.getEnfant().getNom();
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

    
    
}
