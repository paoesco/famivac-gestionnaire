package fr.fava.gestionnaire.interfaces.sejours.web;

import java.io.Serializable;
import java.util.Collections;
import javax.faces.view.ViewScoped;
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

    public void init() {
        lazyModel = new LazySejourDataModel(Collections.EMPTY_LIST);
    }

    public void supprimer(Long id) {
        init(); // recharge des séjours
    }

    public LazySejourDataModel getLazyModel() {
        return lazyModel;
    }

}
