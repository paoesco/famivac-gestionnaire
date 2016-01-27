package fr.fava.gestionnaire.application.famille;

import fr.fava.gestionnaire.domain.famille.Famille;
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

    public FamilleDTO(Famille bean) {
        this.id = bean.getId();
        this.nomReferent = bean.getMembreReferent().getNom();
        this.prenomReferent = bean.getMembreReferent().getPrenom();
        if (bean.getMembreReferent().getCoordonnees() != null) {
            this.telephoneReferent = bean.getMembreReferent().getCoordonnees().getTelephone1();
            this.emailReferent = bean.getMembreReferent().getCoordonnees().getEmail();
        }
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

}
