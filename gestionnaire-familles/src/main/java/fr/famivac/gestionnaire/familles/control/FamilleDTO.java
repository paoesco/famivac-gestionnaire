package fr.famivac.gestionnaire.familles.control;

import fr.famivac.gestionnaire.familles.entity.Famille;
import java.io.Serializable;

/**
 * @author paoesco
 */
public class FamilleDTO implements Serializable {

    private Long id;

    private String nomReferent;

    private String prenomReferent;

    private String telephoneReferent;

    private String emailReferent;

    private boolean radiee;

    private boolean candidature;

    public FamilleDTO(Famille bean) {
        this.id = bean.getId();
        this.nomReferent = bean.getMembreReferent().getNom();
        this.prenomReferent = bean.getMembreReferent().getPrenom();
        if (bean.getMembreReferent().getCoordonnees() != null) {
            this.telephoneReferent = bean.getMembreReferent().getCoordonnees().getTelephone1();
            this.emailReferent = bean.getMembreReferent().getCoordonnees().getEmail();
        }
        this.radiee = bean.isRadiee();
        this.candidature = bean.isCandidature();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomReferent() {
        return nomReferent;
    }

    public void setNomReferent(String nomReferent) {
        this.nomReferent = nomReferent;
    }

    public String getPrenomReferent() {
        return prenomReferent;
    }

    public void setPrenomReferent(String prenomReferent) {
        this.prenomReferent = prenomReferent;
    }

    public String getTelephoneReferent() {
        return telephoneReferent;
    }

    public void setTelephoneReferent(String telephoneReferent) {
        this.telephoneReferent = telephoneReferent;
    }

    public String getEmailReferent() {
        return emailReferent;
    }

    public void setEmailReferent(String emailReferent) {
        this.emailReferent = emailReferent;
    }

    public boolean isRadiee() {
        return radiee;
    }

    public void setRadiee(boolean radiee) {
        this.radiee = radiee;
    }

    public boolean isCandidature() {
        return candidature;
    }

    public void setCandidature(boolean candidature) {
        this.candidature = candidature;
    }

}
