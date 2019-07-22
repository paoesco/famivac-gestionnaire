package fr.famivac.gestionnaire.familles.entity.views;

/**
 *
 * @author paoesco
 */
public class FamilleToImportDTO {

    private Long familleId;

    private String nomReferent;

    private String prenomReferent;

    private String emailReferent;

    protected FamilleToImportDTO() {
        // JPA
    }

    public FamilleToImportDTO(Long familleId, String nomReferent, String prenomReferent, String email) {
        this.familleId = familleId;
        this.nomReferent = nomReferent;
        this.prenomReferent = prenomReferent;
        this.emailReferent = email;
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

}
