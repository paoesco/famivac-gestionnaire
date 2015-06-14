package fr.fava.gestionnaire.interfaces.web.familles;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author paoesco
 */
@Named
@ViewScoped
public class RechercherFamillesForm implements Serializable {

    private String nomReferent;

    private String prenomReferent;

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
