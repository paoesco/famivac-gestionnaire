package fr.fava.gestionnaire.interfaces.sejours.web;

import fr.fava.gestionnaire.application.sejours.AjouterSejourDTO;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * @author paoesco
 */
@Named
@ViewScoped
public class AjouterSejourBean implements Serializable {

    private AjouterSejourDTO form;

    public void init() {
        form = new AjouterSejourDTO();
    }
    
    public void ajouter(){
        Logger.getAnonymousLogger().info("Ajouté !");
    }

    public AjouterSejourDTO getForm() {
        return form;
    }

    public void setForm(AjouterSejourDTO form) {
        this.form = form;
    }

}
