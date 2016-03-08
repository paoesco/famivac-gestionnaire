package fr.famivac.interfaces.web.accompagnateurs;

import fr.famivac.gestionnaire.sejours.control.AccompagnateurService;
import fr.famivac.gestionnaire.sejours.entity.AccompagnateurRepository;
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
