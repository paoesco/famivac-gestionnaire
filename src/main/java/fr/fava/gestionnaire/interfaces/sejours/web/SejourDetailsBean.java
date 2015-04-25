package fr.fava.gestionnaire.interfaces.sejours.web;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * @author paoesco
 */
@Named
@ViewScoped
public class SejourDetailsBean implements Serializable {

    private Long id;

    /**
     * Initialisation du bean.
     */
    public void init() {
    }

    public void update() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
