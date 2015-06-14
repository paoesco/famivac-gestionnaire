package fr.fava.gestionnaire.interfaces.web.sejours;

import fr.fava.gestionnaire.application.sejour.SejourDTO;
import fr.fava.gestionnaire.application.sejour.SejourService;
import java.io.Serializable;
import java.util.List;
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

    @Inject
    private RechercherSejoursForm rechercherForm;

    public void init() {
        lazyModel = new LazySejourDataModel(sejourService.get());
    }

    public void supprimer(Long id) {
        sejourService.delete(id);
        init(); // recharge des séjours
    }

    public void rechercher() {
        List<SejourDTO> sejours = sejourService.rechercher(rechercherForm.getNomReferent(), rechercherForm.getPrenomReferent(), rechercherForm.getNomEnfant(), rechercherForm.getPrenomEnfant());
        lazyModel = new LazySejourDataModel(sejours);
    }

    public LazySejourDataModel getLazyModel() {
        return lazyModel;
    }

}
