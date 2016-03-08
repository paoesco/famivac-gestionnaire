package fr.famivac.interfaces.web.familles;

import fr.famivac.gestionnaire.familles.control.FamilleService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Backing bean des familles.
 *
 * @author paoesco
 */
@Named
@ViewScoped
public class FamillesListeBean implements Serializable {

    /**
     * Liste des familles.
     */
    private LazyFamilleDataModel lazyModel;

    @Inject
    private FamilleService familleService;

    private List<String> periodesAccueil;

    /**
     * Initialisation du bean.
     */
    public void init() {
        periodesAccueil = new ArrayList<>();
        rechercher();
    }

    public void rechercher() {
        lazyModel = new LazyFamilleDataModel(familleService.rechercher("", "", periodesAccueil));
    }

    public void supprimer(Long id) {
        familleService.delete(id);
        init(); // recharge des familles
    }

    public LazyFamilleDataModel getLazyModel() {
        return lazyModel;
    }

    public List<String> getPeriodesAccueil() {
        return periodesAccueil;
    }

    public void setPeriodesAccueil(List<String> periodesAccueil) {
        this.periodesAccueil = periodesAccueil;
    }

}
