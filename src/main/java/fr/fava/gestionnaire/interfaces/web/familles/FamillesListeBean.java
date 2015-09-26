package fr.fava.gestionnaire.interfaces.web.familles;

import fr.fava.gestionnaire.application.famille.FamilleService;
import java.io.Serializable;
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

    @Inject
    private RechercherFamillesForm rechercherForm;

    /**
     * Initialisation du bean.
     */
    public void init() {
        rechercher();
    }

    public void rechercher() {
        lazyModel = new LazyFamilleDataModel(familleService.rechercher(rechercherForm.getNomReferent(), rechercherForm.getPrenomReferent(), rechercherForm.getPeriodesAccueil()));
    }

    public void supprimer(Long id) {
        familleService.delete(id);
        init(); // recharge des familles
    }

    public LazyFamilleDataModel getLazyModel() {
        return lazyModel;
    }

}
