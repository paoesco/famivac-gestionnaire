package fr.famivac.gestionnaire.familles.boundary;

import fr.famivac.gestionnaire.familles.entity.Famille;

/**
 *
 * @author paoesco
 */
public class FamilleToImportDTO {

    private Long familleId;

    private String nomReferent;

    private String prenomReferent;

    private String emailReferent;

    private String status;

    protected FamilleToImportDTO() {
        // JPA
    }

    public FamilleToImportDTO(Famille famille) {
        this.familleId = famille.getId();
        this.nomReferent = famille.getMembreReferent().getNom();
        this.prenomReferent = famille.getMembreReferent().getPrenom();
        this.emailReferent = famille.getMembreReferent().getCoordonnees().getEmail();
        this.status = famille.getStatus().toString();
    }

    public Long getFamilleId() {
        return familleId;
    }

    public void setFamilleId(Long familleId) {
        this.familleId = familleId;
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

    public String getEmailReferent() {
        return emailReferent;
    }

    public void setEmailReferent(String emailReferent) {
        this.emailReferent = emailReferent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
