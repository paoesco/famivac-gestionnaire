package fr.fava.gestionnaire.application.famille;

import fr.fava.gestionnaire.domain.famille.Famille;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author paoesco
 */
@XmlRootElement
public class FamilleDTO {

    private Long id;

    private String nomReferent;

    private String prenomReferent;

    public FamilleDTO(Famille bean) {
        this.id = bean.getId();
        this.nomReferent = bean.getMembreReferent().getNom();
        this.prenomReferent = bean.getMembreReferent().getPrenom();
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

}
