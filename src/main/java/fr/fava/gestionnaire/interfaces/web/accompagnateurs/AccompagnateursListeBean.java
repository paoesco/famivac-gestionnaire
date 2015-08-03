package fr.fava.gestionnaire.interfaces.web.accompagnateurs;

import fr.fava.gestionnaire.application.accompagnateur.AccompagnateurService;
import fr.fava.gestionnaire.interfaces.web.familles.*;
import fr.fava.gestionnaire.application.famille.FamilleService;
import fr.fava.gestionnaire.domain.sejour.AccompagnateurRepository;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Backing bean des accompagnateurs.
 *
 * @author paoesco
 */
@Named
@ViewScoped
public class AccompagnateursListeBean implements Serializable {

    /**
     * Liste des accompagnateurs.
     */
    private LazyAccompagnateurDataModel lazyModel;

    @Inject
    private AccompagnateurRepository accompagnateurRepository;
    
    @Inject
    private AccompagnateurService accompagnateurService;

    /**
     * Initialisation du bean.
     */
    public void init() {
        rechercher();
    }

    public void rechercher() {
        lazyModel = new LazyAccompagnateurDataModel(accompagnateurRepository.get());
    }

    public void supprimer(Long id) {
        accompagnateurService.delete(id);
        init(); // recharge des familles
    }

    public LazyAccompagnateurDataModel getLazyModel() {
        return lazyModel;
    }

}
