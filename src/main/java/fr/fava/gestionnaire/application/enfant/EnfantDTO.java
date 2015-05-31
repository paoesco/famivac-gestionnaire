package fr.fava.gestionnaire.application.enfant;

import fr.fava.gestionnaire.domain.enfant.Enfant;

/**
 *
 * @author paoesco
 */
public class EnfantDTO {

    private long id;

    private String nomEnfant;

    private String prenomEnfant;

    public EnfantDTO(Enfant bean) {
        this.id = bean.getId();
        this.nomEnfant = bean.getNom();
        this.prenomEnfant = bean.getPrenom();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomEnfant() {
        return nomEnfant;
    }

    public void setNomEnfant(String nomEnfant) {
        this.nomEnfant = nomEnfant;
    }

    public String getPrenomEnfant() {
        return prenomEnfant;
    }

    public void setPrenomEnfant(String prenomEnfant) {
        this.prenomEnfant = prenomEnfant;
    }

}
