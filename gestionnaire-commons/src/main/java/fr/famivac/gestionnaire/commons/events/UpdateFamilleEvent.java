package fr.famivac.gestionnaire.commons.events;

/**
 *
 * @author paoesco
 */
public class UpdateFamilleEvent {

    private Long id;
    private String nom;
    private String prenom;
    private String telephone;
    private boolean referent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean isReferent() {
        return referent;
    }

    public void setReferent(boolean referent) {
        this.referent = referent;
    }

}
