package fr.fava.gestionnaire.interfaces.web.familles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

    private List<String> periodesAccueil;

    public RechercherFamillesForm() {
        periodesAccueil = new ArrayList<>();
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

    public List<String> getPeriodesAccueil() {
        return periodesAccueil;
    }

    public void setPeriodesAccueil(List<String> periodesAccueil) {
        this.periodesAccueil = periodesAccueil;
    }

}
