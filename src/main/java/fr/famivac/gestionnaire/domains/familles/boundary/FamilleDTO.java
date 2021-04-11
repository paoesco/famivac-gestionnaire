package fr.famivac.gestionnaire.domains.familles.boundary;

import fr.famivac.gestionnaire.domains.familles.entity.Famille;
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

    private Boolean radiee;

    private Boolean candidature;

    private Boolean archivee;

    public FamilleDTO(Famille bean) {
        this.id = bean.getId();
        this.nomReferent = bean.getMembreReferent().getNom();
        this.prenomReferent = bean.getMembreReferent().getPrenom();
        if (bean.getMembreReferent().getCoordonnees() != null) {
            this.telephoneReferent = bean.getMembreReferent().getCoordonnees().getTelephone1();
            this.emailReferent = bean.getMembreReferent().getCoordonnees().getEmail();
        }
        this.radiee = bean.getRadiee();
        this.candidature = bean.getCandidature();
        this.archivee = bean.getArchivee();
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

    public Boolean getRadiee() {
        return radiee;
    }

    public void setRadiee(Boolean radiee) {
        this.radiee = radiee;
    }

    public Boolean getCandidature() {
        return candidature;
    }

    public void setCandidature(Boolean candidature) {
        this.candidature = candidature;
    }

    public Boolean getArchivee() {
        return archivee;
    }

    public void setArchivee(Boolean archivee) {
        this.archivee = archivee;
    }

}
