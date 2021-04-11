package fr.famivac.gestionnaire.domains.enfants.control;

import fr.famivac.gestionnaire.domains.enfants.entity.Enfant;

/**
 *
 * @author paoesco
 */
public class EnfantDTO {

    private Long id;

    private String nomEnfant;

    private String prenomEnfant;

    public EnfantDTO(Enfant bean) {
        this.id = bean.getId();
        this.nomEnfant = bean.getNom();
        this.prenomEnfant = bean.getPrenom();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
