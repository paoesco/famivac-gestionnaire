package fr.fava.gestionnaire.interfaces.sejours.web;

import fr.fava.gestionnaire.application.sejour.SejourService;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author paoesco
 */
@Named
@ViewScoped
public class SejoursBean implements Serializable {

    /**
     * Liste des séjours.
     */
    private LazySejourDataModel lazyModel;

    @Inject
    private SejourService sejourService;

    public void init() {
        lazyModel = new LazySejourDataModel(sejourService.retrieve());
    }

    public void supprimer(Long id) {
        init(); // recharge des séjours
    }

    public LazySejourDataModel getLazyModel() {
        return lazyModel;
    }

}
