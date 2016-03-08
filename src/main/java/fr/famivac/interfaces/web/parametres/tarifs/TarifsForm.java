package fr.famivac.interfaces.web.parametres.tarifs;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * @author paoesco
 */
@Named
@ViewScoped
public class TarifsForm implements Serializable {

    
    private double fraisDossier;

    private double prixJournalier;

    public TarifsForm() {
        fraisDossier = 0;
        prixJournalier = 0;
    }

    public double getFraisDossier() {
        return fraisDossier;
    }

    public void setFraisDossier(double fraisDossier) {
        this.fraisDossier = fraisDossier;
    }

    public double getPrixJournalier() {
        return prixJournalier;
    }

    public void setPrixJournalier(double prixJournalier) {
        this.prixJournalier = prixJournalier;
    }

}
