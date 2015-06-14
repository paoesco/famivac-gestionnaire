package fr.fava.gestionnaire.interfaces.web.parametres.tarifs;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author paoesco
 */
@Named
@ViewScoped
public class TarifsBean implements Serializable {
    
    @Inject
    private TarifsForm tarifsForm;

    /**
     * Initialisation du bean.
     */
    public void init() {
        tarifsForm.setFraisDossier(12);
        tarifsForm.setPrixJournalier(13.5);
    }
    
    public void sauver(){
        Logger.getAnonymousLogger().info("Tarifs sauvegardés");
    }

}
