package fr.famivac.gestionnaire.sejours.control;

import java.util.Date;

/**
 * @author paoesco
 */
public class VoyageDTO {

    private Long voyageId;

    private Long sejourId;

    private Date dateVoyage;

    private Boolean retour;

    private String enfant;

    private Long enfantId;

    private String famille;

    private Long familleId;

    private String telephoneFamille;

    private Date heureRendezVous;

    private String lieu;

    private Date heureTransport;

    private Date heureArrivee;

    private String numeroTransport;

    private String accompagnateur;

    private String telephoneAccompagnateur;

    private String validationAccompagnateur;

    private String contactParis;

    private String telephoneContactParis;

    public Long getVoyageId() {
        return voyageId;
    }

    public void setVoyageId(Long voyageId) {
        this.voyageId = voyageId;
    }

    public Long getSejourId() {
        return sejourId;
    }

    public void setSejourId(Long sejourId) {
        this.sejourId = sejourId;
    }

    public Date getDateVoyage() {
        return dateVoyage;
    }

    public void setDateVoyage(Date dateVoyage) {
        this.dateVoyage = dateVoyage;
    }

    public Boolean getRetour() {
        return retour;
    }

    public String getRetourLibelle() {
        return getRetour() ? "R" : "A";
    }

    public void setRetour(Boolean retour) {
        this.retour = retour;
    }

    public String getEnfant() {
        return enfant;
    }

    public void setEnfant(String enfant) {
        this.enfant = enfant;
    }

    public Long getEnfantId() {
        return enfantId;
    }

    public void setEnfantId(Long enfantId) {
        this.enfantId = enfantId;
    }

    public String getFamille() {
        return famille;
    }

    public void setFamille(String famille) {
        this.famille = famille;
    }

    public Long getFamilleId() {
        return familleId;
    }

    public void setFamilleId(Long familleId) {
        this.familleId = familleId;
    }

    public String getTelephoneFamille() {
        return telephoneFamille;
    }

    public void setTelephoneFamille(String telephoneFamille) {
        this.telephoneFamille = telephoneFamille;
    }

    public Date getHeureRendezVous() {
        return heureRendezVous;
    }

    public void setHeureRendezVous(Date heureRendezVous) {
        this.heureRendezVous = heureRendezVous;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Date getHeureTransport() {
        return heureTransport;
    }

    public void setHeureTransport(Date heureTransport) {
        this.heureTransport = heureTransport;
    }

    public Date getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(Date heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    public String getNumeroTransport() {
        return numeroTransport;
    }

    public void setNumeroTransport(String numeroTransport) {
        this.numeroTransport = numeroTransport;
    }

    public String getAccompagnateur() {
        return accompagnateur;
    }

    public void setAccompagnateur(String accompagnateur) {
        this.accompagnateur = accompagnateur;
    }

    public String getTelephoneAccompagnateur() {
        return telephoneAccompagnateur;
    }

    public void setTelephoneAccompagnateur(String telephoneAccompagnateur) {
        this.telephoneAccompagnateur = telephoneAccompagnateur;
    }

    public String getValidationAccompagnateur() {
        return validationAccompagnateur;
    }

    public void setValidationAccompagnateur(String validationAccompagnateur) {
        this.validationAccompagnateur = validationAccompagnateur;
    }

    public String getContactParis() {
        return contactParis;
    }

    public void setContactParis(String contactParis) {
        this.contactParis = contactParis;
    }

    public String getTelephoneContactParis() {
        return telephoneContactParis;
    }

    public void setTelephoneContactParis(String telephoneContactParis) {
        this.telephoneContactParis = telephoneContactParis;
    }

}
